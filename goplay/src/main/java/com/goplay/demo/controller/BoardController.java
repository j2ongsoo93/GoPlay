package com.goplay.demo.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.goplay.demo.dto.*;
import com.goplay.demo.service.*;
import com.goplay.demo.vo.*;
import org.modelmapper.TypeToken;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.modelmapper.ModelMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.mail.Multipart;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.ServletContext;

@Controller
@Setter
public class BoardController {
	@Autowired
	private BoardService bs;

	@Autowired
	private ReplyService rs;

	@Autowired
	ClubService cs;

	@Autowired
	private MemberService ms;

	@Autowired
	private MatchBoardService mbs;

	@Autowired
	private ClubMemberlistService cms;

	//listAllClub3.html에서 클릭한 리스트의 동호회 Cno
	int cNoChangHee=0;

	//현재 클럽 정보를 담고 있음.
	List<ClubInfoDTO> clubInfoDTO =null;
	//동호회 커뮤니티 내 클럽 정보
	//SELECT * FROM board WHERE c_no=1 AND SCH_DATE IS NOT NULL and (b_date between '22/05/01' and '22/05/31');
	@PostMapping("/listBoardSch")
	@ResponseBody
	public Page<BoardDTO> listBoardSch(Pageable pageable, @RequestParam @Nullable String thisFirstISO, @RequestParam @Nullable String thisLastISO) {
		int cNo=cNoChangHee;//tiger123  의 Cno
		return bs.listBoardSch(pageable,cNo,thisFirstISO,thisLastISO);
	}
	
	//전체 게시판 글 띄우기
	@GetMapping("/listBoardAllCno")
	@ResponseBody
	public Page<BoardDTO> listBoardAllCno(Pageable pageable) {
		int cNo = cNoChangHee;//cNoChangHee
		return bs.listBoardAllCno(pageable, cNo);
	}

	@PostMapping("/insertBoardWrite")
	@ResponseBody
	public ResponseEntity<List<BoardUploadDTO>> insertBoard(
			@RequestPart @Nullable MultipartFile[] uploadFiles,
			String boardText,
			String datetimepicker1Input,
			String placeInput,
			String currentid,
			HttpSession session,
			HttpServletRequest request
	) {
		LocalDateTime localDateTime =null;
		Board board = new Board();
		List<BoardUploadDTO> boardUploadDTO = new ArrayList<>();
		String realWebPath="";
		String realFileName="";
		System.out.println(currentid);
		if (uploadFiles != null){

			//b_date 포맷 localDateTime으로변경
			try{
				//String -> Date
				SimpleDateFormat oldDtFormat = new SimpleDateFormat("yyyy. MM. dd. a KK:mm");
				Date formatDate1 = oldDtFormat.parse(datetimepicker1Input);
				System.out.println(formatDate1);

				//Date -> LocalDateTime
				localDateTime = formatDate1.toInstant() // Date -> Instant                .
						.atZone(ZoneId.systemDefault()) // Instant -> ZonedDateTime
						.toLocalDateTime(); // ZonedDateTime -> LocalDateTime

				System.out.println("localDateTime " + localDateTime);

			}catch (Exception e){
				System.out.println("message 여기    " + e.getMessage());
			}

			for (MultipartFile uploadFile : uploadFiles) {

				// 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
				String originalName = uploadFile.getOriginalFilename();
				String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
				String uuid = UUID.randomUUID().toString();
				String saveName = "/temp/uploads/img";

//				realWebPath = request.getServletContext().getRealPath(saveName);
				realWebPath = saveName;

				realFileName= uuid +"_" +fileName;
				realWebPath += File.separator + realFileName;
				System.out.println(realWebPath);

				//상대경로에 저장하기 위해서 Path필요한 듯.
				try {
					File saveFile = new File(saveName);
					if(saveFile.exists() == false){
						saveFile.mkdirs();
					}

					Path savePath = Paths.get(realWebPath);
					uploadFile.transferTo(savePath);
					boardUploadDTO.add(new BoardUploadDTO(fileName,uuid, realFileName, realWebPath));

				}catch (IOException e){
					e.printStackTrace();
				}
			}
		}

		//게시글 내용 담아서 전송
		board.setBNo(0);
		board.setBTitle(null); //제목 사용 안함
		board.setBContent(boardText);
		board.setBImg(realFileName);
		board.setBVideo(null); //사용 안함
		board.setBFile(null); //사용 안함
		board.setBDate(LocalDateTime.now());
		board.setBHit(0); //사용 안함
		board.setSchDate(localDateTime);
		board.setSchPlace(placeInput);
		Club club = new Club();
		club.setCNo(cNoChangHee); // cNoChangHee
		//club.setCNo(boardDTO.getCNo());
		board.setClub(club); 				//  로그인 한 사람의 동호회 번호
		board.setBType(1);					// 일단 사용안함, 1이면 경기, 2면 행사, 3이면 경기 게시글
		Member member = new Member();
		member.setId(currentid);  // 로그인 한 사람의 아이디***
		//member.setId(boardDTO.getId());
		board.setMember(member); 			//현재 로그인 한 사람의 id

		bs.saveBoard(board);
		return new ResponseEntity<>(boardUploadDTO, HttpStatus.OK);
	}

	//이미지 출력 용 서비스
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName, String size){
		System.out.println("fileName " + fileName);

		ResponseEntity<byte[]> result = null;

		try {
			String srcFileName = URLDecoder.decode(fileName,"UTF-8");

			File file = new File("/temp/uploads/img" + File.separator + srcFileName);

			if(size != null && size.equals("1")){
				file = new File(file.getParent(),file.getName().substring(2));
			}

			HttpHeaders header = new HttpHeaders();

			//MIME타입 처리
			header.add("Content-Type", Files.probeContentType(file.toPath()));

			//파일 데이터 처리
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		}catch (Exception e){

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	//동호회 메인으로 페이지 이동 될 때 Cno를 가져옴
	@GetMapping("/clubMain")
	public String clubMain(@RequestParam int cNo){
		this.cNoChangHee = cNo;

		System.out.println(cNoChangHee);
		return "clubMain";
	}

	//커뮤니티 게시판 동호회원 목록 출력
	@GetMapping("/findClubMemberCno")
	@ResponseBody
	public List<MemberDTOChangHee> findClubMemberCno() {
		int cNo=cNoChangHee;//cNoChangHee
		return ms.findClubMemberCno(cNo);
	}

	//동호회 가입하기
	@GetMapping("/clubJoin")
	@ResponseBody
	public String clubJoin(@RequestParam String currentid){
		List<ClubMemberlistDTO> cmlDTO = cms.findByCno(cNoChangHee);
		for (ClubMemberlistDTO cDTO : cmlDTO){
			System.out.println(cDTO.getId());
			if (cDTO.getId().equals(currentid)){
				System.out.println("이미 가입되었습니다.");
				return "이미 가입되었습니다.";
			}
		}
		Club club = new Club();
		club.setCNo(cNoChangHee);
		Member member = new Member();
		member.setId(currentid);
		ClubMemberlist clubMemberlist = new ClubMemberlist();
		clubMemberlist.setListNo(0);
		clubMemberlist.setClub(club);
		clubMemberlist.setMember(member);
		cs.joinClub(clubMemberlist);
		return "동호회 가입 완료하였습니다.";
	}
	@GetMapping("/clubDeleteSingle")
	@ResponseBody
	public String clubDeleteSingle(@RequestParam String currentid){
		//delete club_memberlist where c_no=122 and id='whxogjs123'

		//cNoChangHee
		int result = cms.deleteClubMemberList(cNoChangHee,currentid);
		System.out.println("삭제 결과 " +result);
		return "OK";

	}

	//동호회 커뮤니티 내 클럽 정보
	@GetMapping("/getClubProfileResult")
	@ResponseBody
	public List<ClubInfoDTO> getClubProfileResult() {
		int cNo=cNoChangHee;//tiger123  의 Cno
		clubInfoDTO = cs.getClubProfileResult(cNo);
		return clubInfoDTO;
	}

	@PostMapping("/listMatchCno")
	@ResponseBody
	//동호회 커뮤니티 경기 일정 스케쥴 출력
	public Page<MatchBoardDTO> listMatchCno(Pageable pageable, @RequestParam @Nullable String thisFirstISO, @RequestParam @Nullable String thisLastISO) {
		int cNo = cNoChangHee; //cNoChangHee
		return mbs.listMatchCno(pageable, cNo, thisFirstISO, thisLastISO);
	}

	@GetMapping("/listBoardByReply/{id}")	//마이페이지 내 댓글 조회
	@ResponseBody
	public MyPageDTO listBoardByReply(@PathVariable String id){
		MyPageDTO mypage = new MyPageDTO();
		mypage.setBoard(bs.findById(id));
		mypage.setReply(rs.findByReplyId(id));
		return mypage;
	}

	@GetMapping("/listBoard/{id}")	//마이페이지 내 게시물 조회
	@ResponseBody
	public List<BoardDTO> listBoard(@PathVariable String id){
		return bs.findById(id);
	}

	@GetMapping("/deleteBoard/{bNo}")
	@ResponseBody
	public String delete(@PathVariable int bNo) {
		bs.deleteBorad(bNo);
		return "";
	}
}
