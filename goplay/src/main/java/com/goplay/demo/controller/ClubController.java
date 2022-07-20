package com.goplay.demo.controller;

import javax.servlet.http.HttpSession;

import com.goplay.demo.constant.Role;
import com.goplay.demo.dao.MemberDAO;
import com.goplay.demo.dao.MemberDAOCustom;
import com.goplay.demo.dto.BoardUploadDTO;
import com.goplay.demo.dto.ClubInfoDTO;
import com.goplay.demo.searchCondition.RecommentClubCondition;
import com.goplay.demo.vo.Club;
import com.goplay.demo.vo.ClubMemberlist;
import com.goplay.demo.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.goplay.demo.dto.ClubDTO;
import com.goplay.demo.searchCondition.ClubSearchCondition;
import com.goplay.demo.service.ClubService;
import com.goplay.demo.service.MemberService;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Setter
public class ClubController {
	@Autowired
	private ClubService cs;
	@Autowired
	private MemberService ms;

	@Autowired
	private MemberDAO mdao;

	Page<ClubDTO> clubDTOPage;
	int startPage;
	int endpage;

	//동호회 등록 페이지로 이동
	@GetMapping("/insertClub")
	public String insertClub(){
		return "insertClub";
	}

	//동호회 등록
	@PostMapping("/insertClubBtn")
	@ResponseBody
	public String insertClubPost(
			@RequestPart @Nullable MultipartFile[] uploadFiles,
			@RequestParam @Nullable  int cNo,
			@RequestParam @Nullable  String cIntro,
			@RequestParam @Nullable  String cLoc1,
			@RequestParam @Nullable  String cLoc2,
			@RequestParam @Nullable  String cName,
			@RequestParam @Nullable String cStat,
			@RequestParam @Nullable String cType,
			@RequestParam @Nullable String currentid
			){

		String realWebPath="";
		String realFileName="";

		//BoardUploadDTO 재사용
		List<BoardUploadDTO> boardUploadDTO = new ArrayList<>();

		System.out.println(uploadFiles);
		System.out.println(cNo);
		System.out.println(cIntro);
		System.out.println(cLoc1);
		System.out.println(cLoc2);
		System.out.println(cName);
		System.out.println(cStat);
		System.out.println(cType);
		System.out.println(currentid);
		if (uploadFiles != null) {
			for (MultipartFile uploadFile : uploadFiles) {
				// 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
				String originalName = uploadFile.getOriginalFilename();
				String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
				String uuid = UUID.randomUUID().toString();
				String saveName = "/temp/uploads/img";

				realWebPath = saveName;

				realFileName = uuid + "_" + fileName;
				realWebPath += File.separator + realFileName;

				//상대경로에 저장하기 위해서 Path필요한 듯.
				try {
					File saveFile = new File(saveName);
					if (saveFile.exists() == false) {
						saveFile.mkdirs();
					}

					Path savePath = Paths.get(realWebPath);
					uploadFile.transferTo(savePath);
					boardUploadDTO.add(new BoardUploadDTO(fileName, uuid, realFileName, realWebPath));

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//Club insert 용
		Club club = new Club();
		club.setCNo(0);//이미 존재하는 클럽 번호전송 (어차피 자동 생성)
		Member member = new Member();
		member.setId(currentid);
		club.setMember(member);
		club.setCName(cName);
		club.setCType(cType);
		club.setCLoc1(cLoc1);
		club.setCLoc2(cLoc2);
		club.setCImg(realFileName);
		club.setCIntro(cIntro);
		club.setCStat(cStat);

		//클럽 생성과 동시에 해당 클럽 생성자 멤버리스트에 추가
		ClubMemberlist clubMemberlist = new ClubMemberlist();
		clubMemberlist.setListNo(0);
		clubMemberlist.setClub(club);
		clubMemberlist.setMember(member);

		//채팅방 insert는 미구현 예정이라 우선 진행 X

		cs.saveClub(club, clubMemberlist);

		return "OK";
	}


	//클럽 검색 기능(listClubAll)
	@GetMapping("/listClubAll3")
	@ResponseBody
	public Page<ClubDTO> listClubAll(Pageable pageable, HttpSession session, Model model,
									 @RequestParam(defaultValue = "") String searchText,
									 @RequestParam(defaultValue = "축구") String cType,
									 @RequestParam(defaultValue = "") String cloc1,
									 @RequestParam(defaultValue = "") String cloc2
	){
		System.out.println("cType " + cType);
		System.out.println("cloc1 " + cloc1);
		System.out.println("cloc2 " + cloc2);
		ClubSearchCondition condition = new ClubSearchCondition();
		condition.setC_type(cType);
		condition.setC_loc1(cloc1);
		condition.setC_loc2(cloc2);
		condition.setC_keyword(searchText);
		System.out.println("searchText " + searchText);
		clubDTOPage =  cs.listClubAll(pageable ,condition); // List타입 to Page 타입으로 변환
		return clubDTOPage;
	}

	@GetMapping("/listRecommendClub")
	@ResponseBody
	public Page<ClubDTO> listRecommendClub(Pageable pageable, @RequestParam @Nullable String currentid) {

		//String id="tiger123"; //로그인 한 id 값
		System.out.println("currentid " + currentid);
		Member memberInfo = ms.findByIdTypeLoc(currentid);
		String[] resultType = {"","","","","",""}; // type 숫자 -> 스트링 변환 후 저장 (1 -> 축구)
		//축구, 풋살, 족구, 농구, 지역1, 지역2
		if (memberInfo.getSoccer() == 1) {
			resultType[0] = "축구";
		}
		if(memberInfo.getFootsal() == 1) {
			resultType[1] = "풋살";
		}
		if(memberInfo.getFootvalleyball() == 1 ) {
			resultType[2] = "족구";
		}
		if(memberInfo.getBascketball() == 1) {
			resultType[3] = "농구";
		}

		resultType[4] = memberInfo.getMLoc1();
		resultType[5] = memberInfo.getMLoc2();

		RecommentClubCondition condition = new RecommentClubCondition();
		condition.setSoccer(resultType[0]);
		condition.setFootsal(resultType[1]);
		condition.setFootvalleyball(resultType[2]);
		condition.setBascketball(resultType[3]);
		condition.setC_loc1(resultType[4]);
		condition.setC_loc2(resultType[5]);

		return cs.listRecommendClub(pageable ,condition);
	}

	//추천 동호회 출력(랜덤하게 2개출력)
	@GetMapping("/listAllClub")
	@ResponseBody
	public List<ClubDTO> listAllClub(){
		List<ClubDTO> cdto =cs.listAllClub();
		List<ClubDTO> cdto2 = cs.listAllClub();
		cdto2.clear();
		for (int i=0; i<cdto.size(); i++) {
			int intValue = (int)(Math.random()*cdto.size());
			cdto2.add(cdto.get(intValue));
		}
		return cdto2;
	}

	// 클럽번호로 클럽 검색(matchBoard에서 사용)
	@GetMapping("/findClub/{cNo}")
	@ResponseBody
	public List<ClubDTO> findClub(@PathVariable int cNo){
		return cs.findClub(cNo);
	}

}
