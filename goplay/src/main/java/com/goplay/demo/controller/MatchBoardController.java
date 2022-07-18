package com.goplay.demo.controller;

import com.goplay.demo.dto.MatchBoardDTO;
import com.goplay.demo.dto.MatchDateDTO;
import com.goplay.demo.dto.MatchRecordDTO;
import com.goplay.demo.searchCondition.MatchBoardSearchCondition;
import com.goplay.demo.service.MatchRecordService;
import com.goplay.demo.vo.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.goplay.demo.service.MatchBoardService;
import com.goplay.demo.vo.MatchBoard;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@Setter
public class MatchBoardController {
	@Autowired
	private MatchBoardService ms;
	@Autowired
	private MatchRecordService mrs;

	//매치검색페이지로 이동
	@GetMapping("/matchBoard")
	public String listMatchBoard(Model model){
		return "matchBoard";
	}

	//매치검색
	@PostMapping("/findMatch")
	@ResponseBody
	public Page<MatchBoardDTO> findMatchBoard(@RequestBody HashMap<String, String> map)	{
		List<String> mbStat = new ArrayList<>();
		mbStat.add(map.get("mbStat_wait"));
		mbStat.add(map.get("mbStat_matched"));
		mbStat.add(map.get("mbStat_end"));
		mbStat.removeAll(Arrays.asList("",null));
		if(map.get("mbStat_wait")==null && map.get("mbStat_matched")==null && map.get("mbStat_end")==null){
			mbStat = null;
		}

		String mbDate;
		LocalDateTime mbDateTime;
		if(map.get("mbDate") == null){
			mbDateTime = null;
		}else{
			mbDate = map.get("mbDate");
			mbDate = mbDate.substring(0,19);
			mbDate = mbDate.replace("T", " ");
			mbDate = mbDate.replace("Z", "");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			mbDateTime = LocalDateTime.parse(mbDate, formatter);
			System.out.println(mbDateTime);
		}

		MatchBoardSearchCondition condition = new MatchBoardSearchCondition(mbDateTime, map.get("mbType"), map.get("mbLoc1"), map.get("mbLoc2"), mbStat);
		PageRequest pageRequest = PageRequest.of(Integer.parseInt(map.get("page")), Integer.parseInt(map.get("size")));
		return ms.searchMatchBoard(condition, pageRequest);
	}

	//매치등록, 수정
	@PostMapping("/saveMatchBoard")
	@ResponseBody
	public void insertBoard(@RequestBody Map<String, String> map) {
		String mbDate;
		LocalDateTime mbDateTime;
		if(map.get("mbDate") == null){
			mbDateTime = null;
		}else{
			mbDate = map.get("mbDate");
			mbDate = mbDate.substring(0,19);
			mbDate = mbDate.replace("T", " ");
			mbDate = mbDate.replace("Z", "");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			mbDateTime = LocalDateTime.parse(mbDate, formatter);
			System.out.println(mbDateTime);
		}

		MatchBoard mb = new MatchBoard();
		Club c = new Club();
//		c.setCNo(Integer.parseInt(map.get("homeClub")));
		c.setCNo(1);
		mb.setClub(c);
		mb.setMbDate(mbDateTime);
		mb.setMbType(map.get("mbType"));
		mb.setMbLoc1(map.get("mbLoc1"));
		mb.setMbLoc2(map.get("mbLoc2"));
		mb.setMbStadium(map.get("mbStadium"));
		mb.setMbFee(map.get("mbFee"));
		mb.setHomeUcolor(map.get("homeUcolor"));
		mb.setHomeLevel(map.get("homeLevel"));
		mb.setHomeSay(map.get("homeSay"));
		mb.setMbStat(map.get("mbStat"));
		ms.saveBoard(mb);
	}

	//매치등록화면으로 이동
	@GetMapping("/insertMatchBoard")
	public String insertBoard(Model model){
		return "insertMatchBoard";
	}

	//매치수정화면으로 이동
	@GetMapping("/updateMatchBoard")
	public void updateBoard(){
	}

	//내 매치 검색
	@GetMapping("/myMatchEnd/{id}")
	@ResponseBody
	public List<MatchBoard> myMatch(@PathVariable String id){
		return ms.myMatch(id);
	}

	//전적 검색
	@GetMapping("/matchRecord/{cNo}")
	@ResponseBody
	public List<MatchRecordDTO> matchRecord(@PathVariable int cNo){
		return mrs.matchRecord(cNo);
	}

	@PostMapping("/listMatchCno")
	@ResponseBody
	//동호회 커뮤니티 경기 일정 스케쥴 출력
	public Page<MatchBoardDTO> listMatchCno(Pageable pageable, @RequestParam @Nullable String thisFirstISO, @RequestParam @Nullable String thisLastISO) {

		int cNo = 1; //현재 로그인 한 id의 동호회 cno 받아와아햠

		return ms.listMatchCno(pageable, cNo, thisFirstISO, thisLastISO);
	}

	// 날짜별 매치 수 조회
	@GetMapping("/matchDate")
	@ResponseBody
	public List<MatchDateDTO> matchDate(){
		return ms.matchDate();
	}

}
