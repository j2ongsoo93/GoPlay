package com.goplay.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.goplay.demo.dto.BoardUploadDTO;
import com.goplay.demo.vo.Board;
import com.goplay.demo.vo.MatchBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

@Controller
@Setter
public class BoardController {
	@Autowired
	private BoardService bs;

	@Autowired
	private ReplyService rs;

	@Autowired
	private ServletContext sct;
	
	//동호회 커뮤니티 내 클럽 정보
	@GetMapping("/listBoardSch")
	@ResponseBody
	public Page<BoardDTO> listBoardSch(Pageable pageable) {
		int cNo=1;//tiger123  의 Cno
		return bs.listBoardSch(pageable,cNo);
	}
	
	//전체 게시물 띄우기
	@GetMapping("/listBoardAllCno")
	@ResponseBody
	public Page<BoardDTO> listBoardAllCno(Pageable pageable) {
		int cNo = 1;//tiger123  의 Cno
		return bs.listBoardAllCno(pageable, cNo);
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


	@Value("${com.example.upload.path}") // application.properties의 변수
	private String uploadPath;
	@PostMapping("/insertBoardWrite")
	@ResponseBody
	public ResponseEntity<List<BoardUploadDTO>> insertBoard(
			@RequestPart MultipartFile[] uploadFiles,
			String boardText,
			String datetimepicker1Input,
			String placeInput
	) {

		Board board = new Board();
		BoardDTO boardDTO = new BoardDTO();
		List<BoardUploadDTO> boardUploadDTO = new ArrayList<>();
		String realWebPath="";


		//Date date = new Date(datetimepicker1Input);
		//DateFormat formatter = new SimpleDateFormat("yyyy. MM. dd. a hh:mm");
		//System.out.println(formatter.format(datetimepicker1Input));
		//formatter.parse(datetimepicker1Input);
//		System.out.println(date); // Sat Jun 19 21:05:07 KST 2021
//		System.out.println(datetimepicker1Input);

//		try{
//			System.out.println("datetimepicker1Input "+datetimepicker1Input);
//			String dateStr = datetimepicker1Input;
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy. MM. dd. a HH:mm");
//
//
//			Date date = formatter.parse(dateStr);
//			System.out.println("성공 date " + date); // Sat Jun 19 21:05:07 KST 2021
//		}catch (Exception e){
//			System.out.println("message " + e.getMessage());
//		}

		try{
			String strDate = datetimepicker1Input;
			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy. MM. dd. a KK:mm");
			SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy. MM. dd. a KK:mm");
			// String 타입을 Date 타입으로 변환
			Date formatDate = dtFormat.parse(strDate);
			// Date타입의 변수를 새롭게 지정한 포맷으로 변환
			String strNewDtFormat = newDtFormat.format(formatDate);
			System.out.println("포맷 전 : " + strDate);
			System.out.println("포맷 후 : " + strNewDtFormat);
		}catch (Exception e){
			System.out.println("message " + e.getMessage());
		}


		for (MultipartFile uploadFile : uploadFiles) {

/*			// 이미지 파일만 업로드 가능
			if(uploadFile.getContentType().startsWith("image") == false){
				return "이미지만 가능";
			}*/

			// 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
			String originalName = uploadFile.getOriginalFilename();

			String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

			//UUID
			String uuid = UUID.randomUUID().toString();

			//저장할 파일 이름 중간에 "_"를 이용해 구분
			//String saveName = uploadPath + File.separator + folderPath + File.separator + uuid +"_" +fileName;
			//Path savePath = Paths.get(saveName);

			String saveName = "/static/uploads";
			realWebPath = sct.getRealPath(saveName);
			System.out.println("realWebPath " + realWebPath);
			System.out.println("saveName " + saveName);

			File savePath = new File(realWebPath);
			if(savePath.exists() == false){
				savePath.mkdirs();
			}

			String realFileName=File.separator + fileName +  uuid +"_" +fileName;
			realWebPath += realFileName;
			try {
				File saveFile = new File(realWebPath);
				uploadFile.transferTo(saveFile);
				boardUploadDTO.add(new BoardUploadDTO(fileName,uuid,realWebPath));

			}catch (IOException e){
				e.printStackTrace();
			}


		}
		boardDTO.setBTitle("");
		boardDTO.setBContent(boardText);
		boardDTO.setBImg(realWebPath);
		boardDTO.setBVideo("");
		boardDTO.setBFile("");
		//boardDTO.setBDate();
//		boardDTO.setSchDate(datetimepicker1Input.da);
		boardDTO.setSchPlace(placeInput);
		boardDTO.setCNo(1); //  로그인 한 사람의 동호회 번호
		boardDTO.setBType(1);// 1이면 경기, 2면 행사
		boardDTO.setId("tiger123"); //현재 로그인 한 사람의 id

		//bs.saveBoard(new Board(board.set));
		return new ResponseEntity<>(boardUploadDTO, HttpStatus.OK);

	}

	//업로드 파일이 저장 될 날짜 폴더 생성
//	private String makeFolder() {
//		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//		String folderPath = str.replace("/", File.separator);
//		// make folder ----
//		File uploadPatheFolder = new File(uploadPath,folderPath);
//		if(uploadPatheFolder.exists() == false){
//			uploadPatheFolder.mkdirs();
//		}
//		return folderPath;
//	}
}
