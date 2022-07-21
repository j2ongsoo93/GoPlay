package com.goplay.demo.controller;

import com.goplay.demo.dto.MatchBoardDTO;
import com.goplay.demo.dto.MatchDateDTO;
import com.goplay.demo.dto.MatchOfferDTO;
import com.goplay.demo.dto.MatchRecordDTO;
import com.goplay.demo.searchCondition.MatchBoardSearchCondition;
import com.goplay.demo.service.MatchOfferService;
import com.goplay.demo.service.MatchRecordService;
import com.goplay.demo.vo.Club;
import com.goplay.demo.vo.MatchOffer;
import com.goplay.demo.vo.MatchRecord;
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
	@Autowired
	private MatchOfferService mos;

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

	//매치등록
	@PostMapping("/saveMatchBoard")
	@ResponseBody
	public void insertBoard(@RequestBody Map<String, String> map) {
		//매치 등록 시 사용
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
		if(map.get("mbNo") != null){
			mb.setMb_no(Integer.parseInt(map.get("mbNo")));
		}
		Club c = new Club();
		if(map.get("homeClub") != null){
			c.setCNo(Integer.parseInt(map.get("homeClub")));
			mb.setClub(c);
		}
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
		
		// 매치 수락 시 사용
		if(map.get("awayClub") != null){
			mb.setAwayClub(Integer.parseInt(map.get("awayClub")));
		}
		mb.setAwayUcolor(map.get("awayUcolor"));
		mb.setAwayLevel(map.get("awayLevel"));
		mb.setAwaySay(map.get("awaySay"));
		
		// 매치 종료 시 사용
		if(map.get("hScore") != null){
			mb.setHScore(Integer.parseInt(map.get("hScore")));
		}
		if(map.get("aScore") != null){
			mb.setAScore(Integer.parseInt(map.get("aScore")));
		}
		ms.saveBoard(mb);
	}

	//매치등록화면으로 이동
	@GetMapping("/insertMatchBoard")
	public String insertBoard(Model model){
		return "insertMatchBoard";
	}

	//매치수정화면으로 이동
	@GetMapping("/editMatch/{mbNo}")
	public String updateBoard(Model model, @PathVariable int mbNo){
		model.addAttribute("mbNo", mbNo);
		return "editMatch";
	}

	//매치 상세정보(대기)로 이동
	@GetMapping("/matchDetail/{mbNo}")
	public String detailMatch(Model model, @PathVariable int mbNo){
		model.addAttribute("mbNo", mbNo);
		return "matchDetail";
	}

	//매치 상세정보(성사, 종료)로 이동
	@GetMapping("/matchDetailME/{mbNo}")
	public String detailMatchMatched(Model model, @PathVariable int mbNo){
		model.addAttribute("mbNo", mbNo);
		return "matchDetailME";
	}

	//매치 신청으로 이동
	@GetMapping("/matchOffer/{mbNo}")
	public String offerMatch(Model model, @PathVariable int mbNo){
		model.addAttribute("mbNo", mbNo);
		return "offerMatch";
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


	// 날짜별 매치 수 조회
	@GetMapping("/matchDate")
	@ResponseBody
	public List<MatchDateDTO> matchDate(){
		return ms.matchDate();
	}

	// 매치 번호로 매치 조회
	@GetMapping("/detailMatch/{mbNo}")
	@ResponseBody
	public List<MatchBoardDTO> findByMbNo(@PathVariable int mbNo){
		return ms.findByMbNo(mbNo);
	}

	//매치 번호로 매치 신청내역 조회
	@GetMapping("/listMatchOffer/{mbNo}")
	@ResponseBody
	public List<MatchOfferDTO> listMatchOffer(@PathVariable int mbNo){return mos.listMatchOffer(mbNo);}

	//매치 신청 insert
	@PostMapping("/insertMatchOffer")
	@ResponseBody
	public void insertMatchOffer(@RequestBody HashMap<String, String> map){
		MatchOffer mo = new MatchOffer();

		MatchBoard mb = new MatchBoard();
		if(map.get("mbNo") != null){
			mb.setMb_no(Integer.parseInt(map.get("mbNo")));
			mo.setMatch_board(mb);
		}

		Club c = new Club();
		if(map.get("cNo") != null){
			c.setCNo(Integer.parseInt(map.get("cNo")));
			mo.setClub(c);
		}

		mo.setMoUcolor(map.get("moUcolor"));
		mo.setMoLevel(map.get("moLevel"));
		mo.setMoSay(map.get("moSay"));

		LocalDateTime dt = LocalDateTime.now();
		mo.setMoDate(dt);
		mos.insertMatchOffer(mo);
	}

	//매치신청 번호로 매치 신청내역 조회
	@GetMapping("/findMatchOffer/{moNo}")
	@ResponseBody
	public List<MatchOfferDTO> moByMoNo(@PathVariable int moNo){return mos.moByMoNo(moNo);}

	//매치 종료 후 스코어 입력
	@PostMapping("/insertScore")
	@ResponseBody
	public void insertRecord(@RequestBody HashMap<String, String> map){
		MatchRecord mr1 = new MatchRecord();
		MatchRecord mr2 = new MatchRecord();

		Club c1 = new Club();
		if(map.get("homeClub")!=null){
			c1.setCNo(Integer.parseInt(map.get("homeClub")));
			mr1.setClub(c1);
		}
		Club c2 = new Club();
		if(map.get("awayClub")!=null){
			c2.setCNo(Integer.parseInt(map.get("awayClub")));
			mr2.setClub(c2);
		}

		MatchBoard mb = new MatchBoard();
		if(map.get("mbNo")!=null){
			mb.setMb_no(Integer.parseInt(map.get("mbNo")));
			mr1.setMatch_board(mb);
			mr2.setMatch_board(mb);
		}
		int hScore = 0;
		int aScore = 0;
		if(map.get("hScore") != null && map.get("aScore") != null){
			hScore = Integer.parseInt(map.get("hScore"));
			aScore = Integer.parseInt(map.get("aScore"));
		}
		if(hScore > aScore){
			mr1.setWin(1);
			mr1.setDraw(0);
			mr1.setLose(0);
			mr2.setWin(0);
			mr2.setDraw(0);
			mr2.setLose(1);
		}else if(hScore < aScore){
			mr1.setWin(0);
			mr1.setDraw(0);
			mr1.setLose(1);
			mr2.setWin(1);
			mr2.setDraw(0);
			mr2.setLose(0);
		}else{
			mr1.setWin(0);
			mr1.setDraw(1);
			mr1.setLose(0);
			mr2.setWin(0);
			mr2.setDraw(1);
			mr2.setLose(0);
		}
		mrs.insertRecord(mr1);
		mrs.insertRecord(mr2);
	}
}
