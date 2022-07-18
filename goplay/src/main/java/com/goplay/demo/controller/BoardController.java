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
import com.goplay.demo.vo.Member;
import org.modelmapper.TypeToken;
import com.goplay.demo.dto.BoardUploadDTO;
import com.goplay.demo.vo.Board;
import com.goplay.demo.vo.Club;
import com.goplay.demo.vo.MatchBoard;
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
import com.goplay.demo.dto.BoardDTO;
import com.goplay.demo.dto.MyPageDTO;
import com.goplay.demo.service.BoardService;
import com.goplay.demo.service.ReplyService;
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
	private ServletContext sct;

	@Autowired
	private ReplyService rs;

	@Autowired
	private ServletContext sct;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ResourceLoader resourceLoader;

	//동호회 커뮤니티 내 클럽 정보
	//SELECT * FROM board WHERE c_no=1 AND SCH_DATE IS NOT NULL and (b_date between '22/05/01' and '22/05/31');
	@PostMapping("/listBoardSch")
	@ResponseBody
	public Page<BoardDTO> listBoardSch(Pageable pageable, @RequestParam @Nullable String thisFirstISO, @RequestParam @Nullable String thisLastISO) {
		int cNo=1;//tiger123  의 Cno
		return bs.listBoardSch(pageable,cNo,thisFirstISO,thisLastISO);
	}
	
	//전체 게시판 글 띄우기
	@GetMapping("/listBoardAllCno")
	@ResponseBody
	public Page<BoardDTO> listBoardAllCno(Pageable pageable) {
		int cNo = 1;//tiger123  의 Cno
		return bs.listBoardAllCno(pageable, cNo);
	}

	@PostMapping("/insertBoardWrite") //Board에만 insert -> Club Cno, member mid
	@ResponseBody
	public ResponseEntity<List<BoardUploadDTO>> insertBoard(
			@RequestPart @Nullable MultipartFile[] uploadFiles,
			String boardText,
			String datetimepicker1Input,
			String placeInput,
			HttpSession session,
			HttpServletRequest request
	) {
		LocalDateTime localDateTime =null;
		Board board = new Board();
		List<BoardUploadDTO> boardUploadDTO = new ArrayList<>();
		String realWebPath="";
		String realFileName="";
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
				//String saveName = "C:/temp/uploads/img";
				String saveName = "/temp/uploads/img";

//				realWebPath = request.getServletContext().getRealPath(saveName);
				realWebPath = saveName;

				realFileName= uuid +"_" +fileName;
				System.out.println("realFileName  " + realFileName);
				realWebPath += File.separator + realFileName;
				System.out.println("realWebPath1 " + realWebPath);
//				System.out.println("saveName " + saveName);

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
		board.setBTitle(null);
		board.setBContent(boardText);
		board.setBImg(realFileName);
		board.setBVideo(null);
		board.setBFile(null);
		board.setBDate(LocalDateTime.now());
		board.setBHit(0);
		board.setSchDate(localDateTime);
		board.setSchPlace(placeInput);
		Club club = new Club();
		club.setCNo(1);
		//club.setCNo(boardDTO.getCNo());
		board.setClub(club); 				//  로그인 한 사람의 동호회 번호
		board.setBType(1);					// 1이면 경기, 2면 행사, 3이면 경기 게시글
		Member member = new Member();
		member.setId("hippo123");
		//member.setId(boardDTO.getId());
		board.setMember(member); 			//현재 로그인 한 사람의 id

		//BoardDTO	Club
		//클럽,멤버,게시판

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

			System.out.println("srcFileName " + srcFileName);
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
