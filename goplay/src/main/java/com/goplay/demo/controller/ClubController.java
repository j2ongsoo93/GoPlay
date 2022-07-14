package com.goplay.demo.controller;

import javax.servlet.http.HttpSession;

import com.goplay.demo.dto.ClubInfoDTO;
import com.goplay.demo.searchCondition.RecommentClubCondition;
import com.goplay.demo.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.goplay.demo.dto.ClubDTO;
import com.goplay.demo.searchCondition.ClubSearchCondition;
import com.goplay.demo.service.ClubService;
import com.goplay.demo.service.MemberService;
import lombok.Setter;

import java.util.List;

@Controller
@Setter
public class ClubController {
	@Autowired
	private ClubService cs;
	@Autowired
	private MemberService ms;
	
	Page<ClubDTO> clubDTOPage;
	int startPage;
	int endpage;
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
		//condition.setC_loc1(cloc1); //우선 주석했기 때문에 pageing 시 null 나옴
		//condition.setC_loc2(cloc2); //우선 주석했기 때문에 pageing 시 null 나옴
		condition.setC_keyword(searchText);
		clubDTOPage =  cs.listClubAll(pageable ,condition); // List타입 to Page 타입으로 변환
		return clubDTOPage;
	}

	@GetMapping("/listRecommendClub")
	@ResponseBody
	public Page<ClubDTO> listRecommendClub(Pageable pageable) {
//		public <S extends ClubDTO> Iterable<S> listRecommendClub() {
		String id="tiger123"; //로그인 한 id 값
//		String[] result = (ms.findByIdTypeLoc(id)).split(",");
		Member memberInfo = ms.findByIdTypeLoc(id);
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

		resultType[4] = memberInfo.getMLoc1().toString();
		resultType[5] = memberInfo.getMLoc2().toString();

		RecommentClubCondition condition = new RecommentClubCondition();
		condition.setSoccer(resultType[0]);
		condition.setFootsal(resultType[1]);
		condition.setFootvalleyball(resultType[2]);
		condition.setBascketball(resultType[3]);
		condition.setC_loc1(resultType[4]);
		condition.setC_loc2(resultType[5]);

//		System.out.println("resultType[0] "+ resultType[0]);
//		System.out.println("resultType[1] "+ resultType[1]);
//		System.out.println("resultType[2] "+ resultType[2]);
//		System.out.println("resultType[3] "+ resultType[3]);
//		System.out.println("resultType[4] "+ resultType[4]);
//		System.out.println("resultType[5] "+ resultType[5]);

//		if(cs.listRecommendClub(pageable ,condition).getContent().size()==0) {
//
//		}
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
			System.out.println("qqq" + intValue);
		}
		return cdto2;
	}

	//동호회 커뮤니티 내 클럽 정보
	@GetMapping("/getClubProfileResult")
	@ResponseBody
	public List<ClubInfoDTO> getClubProfileResult() {
		int cNo=1;//tiger123  의 Cno
		return cs.getClubProfileResult(cNo);
	}

}
