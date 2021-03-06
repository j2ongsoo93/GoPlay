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

	//listAllClub3.html?????? ????????? ???????????? ????????? Cno
	int cNoChangHee=0;

	//?????? ?????? ????????? ?????? ??????.
	List<ClubInfoDTO> clubInfoDTO =null;
	//????????? ???????????? ??? ?????? ??????
	//SELECT * FROM board WHERE c_no=1 AND SCH_DATE IS NOT NULL and (b_date between '22/05/01' and '22/05/31');
	@PostMapping("/listBoardSch")
	@ResponseBody
	public Page<BoardDTO> listBoardSch(Pageable pageable, @RequestParam @Nullable String thisFirstISO, @RequestParam @Nullable String thisLastISO) {
		int cNo=cNoChangHee;//tiger123  ??? Cno
		return bs.listBoardSch(pageable,cNo,thisFirstISO,thisLastISO);
	}
	
	//?????? ????????? ??? ?????????
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

			//b_date ?????? localDateTime????????????
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
				System.out.println("message ??????    " + e.getMessage());
			}

			for (MultipartFile uploadFile : uploadFiles) {

				// ?????? ?????? ?????? IE??? Edge??? ?????? ????????? ???????????????
				String originalName = uploadFile.getOriginalFilename();
				String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
				String uuid = UUID.randomUUID().toString();
				String saveName = "/temp/uploads/img";

//				realWebPath = request.getServletContext().getRealPath(saveName);
				realWebPath = saveName;

				realFileName= uuid +"_" +fileName;
				realWebPath += File.separator + realFileName;
				System.out.println(realWebPath);

				//??????????????? ???????????? ????????? Path????????? ???.
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

		//????????? ?????? ????????? ??????
		board.setBNo(0);
		board.setBTitle(null); //?????? ?????? ??????
		board.setBContent(boardText);
		board.setBImg(realFileName);
		board.setBVideo(null); //?????? ??????
		board.setBFile(null); //?????? ??????
		board.setBDate(LocalDateTime.now());
		board.setBHit(0); //?????? ??????
		board.setSchDate(localDateTime);
		board.setSchPlace(placeInput);
		Club club = new Club();
		club.setCNo(cNoChangHee); // cNoChangHee
		//club.setCNo(boardDTO.getCNo());
		board.setClub(club); 				//  ????????? ??? ????????? ????????? ??????
		board.setBType(1);					// ?????? ????????????, 1?????? ??????, 2??? ??????, 3?????? ?????? ?????????
		Member member = new Member();
		member.setId(currentid);  // ????????? ??? ????????? ?????????***
		//member.setId(boardDTO.getId());
		board.setMember(member); 			//?????? ????????? ??? ????????? id

		bs.saveBoard(board);
		return new ResponseEntity<>(boardUploadDTO, HttpStatus.OK);
	}

	//????????? ?????? ??? ?????????
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

			//MIME?????? ??????
			header.add("Content-Type", Files.probeContentType(file.toPath()));

			//?????? ????????? ??????
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		}catch (Exception e){

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	//????????? ???????????? ????????? ?????? ??? ??? Cno??? ?????????
	@GetMapping("/clubMain")
	public String clubMain(@RequestParam int cNo){
		this.cNoChangHee = cNo;

		System.out.println(cNoChangHee);
		return "clubMain";
	}

	//???????????? ????????? ???????????? ?????? ??????
	@GetMapping("/findClubMemberCno")
	@ResponseBody
	public List<MemberDTOChangHee> findClubMemberCno() {
		int cNo=cNoChangHee;//cNoChangHee
		return ms.findClubMemberCno(cNo);
	}

	//????????? ????????????
	@GetMapping("/clubJoin")
	@ResponseBody
	public String clubJoin(@RequestParam String currentid){
		List<ClubMemberlistDTO> cmlDTO = cms.findByCno(cNoChangHee);
		for (ClubMemberlistDTO cDTO : cmlDTO){
			System.out.println(cDTO.getId());
			if (cDTO.getId().equals(currentid)){
				System.out.println("?????? ?????????????????????.");
				return "?????? ?????????????????????.";
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
		return "????????? ?????? ?????????????????????.";
	}
	@GetMapping("/clubDeleteSingle")
	@ResponseBody
	public String clubDeleteSingle(@RequestParam String currentid){
		//delete club_memberlist where c_no=122 and id='whxogjs123'

		//cNoChangHee
		int result = cms.deleteClubMemberList(cNoChangHee,currentid);
		System.out.println("?????? ?????? " +result);
		return "OK";

	}

	//????????? ???????????? ??? ?????? ??????
	@GetMapping("/getClubProfileResult")
	@ResponseBody
	public List<ClubInfoDTO> getClubProfileResult() {
		int cNo=cNoChangHee;//tiger123  ??? Cno
		clubInfoDTO = cs.getClubProfileResult(cNo);
		return clubInfoDTO;
	}

	@PostMapping("/listMatchCno")
	@ResponseBody
	//????????? ???????????? ?????? ?????? ????????? ??????
	public Page<MatchBoardDTO> listMatchCno(Pageable pageable, @RequestParam @Nullable String thisFirstISO, @RequestParam @Nullable String thisLastISO) {
		int cNo = cNoChangHee; //cNoChangHee
		return mbs.listMatchCno(pageable, cNo, thisFirstISO, thisLastISO);
	}

	@GetMapping("/listBoardByReply/{id}")	//??????????????? ??? ?????? ??????
	@ResponseBody
	public MyPageDTO listBoardByReply(@PathVariable String id){
		MyPageDTO mypage = new MyPageDTO();
		mypage.setBoard(bs.findById(id));
		mypage.setReply(rs.findByReplyId(id));
		return mypage;
	}

	@GetMapping("/listBoard/{id}")	//??????????????? ??? ????????? ??????
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
