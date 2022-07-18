//package com.goplay.demo.controller;
//
//import com.goplay.demo.dto.BoardDTO;
//import com.goplay.demo.dto.MyPageDTO;
//import com.goplay.demo.service.BoardService;
//import com.goplay.demo.service.ReplyService;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//@Controller
//@Setter
//public class BoardController_bak {
//	@Autowired
//	private BoardService bs;
//
//	@Autowired
//	private ReplyService rs;
//
//	//동호회 커뮤니티 내 클럽 정보
//	@GetMapping("/listBoardSch")
//	@ResponseBody
//	public Page<BoardDTO> listBoardSch(Pageable pageable) {
//		int cNo=1;//tiger123  의 Cno
//		return bs.listBoardSch(pageable,cNo);
//	}
//
//	//전체 게시물 띄우기
//	@GetMapping("/listBoardAllCno")
//	@ResponseBody
//	public Page<BoardDTO> listBoardAllCno(Pageable pageable) {
//		int cNo = 1;//tiger123  의 Cno
//		return bs.listBoardAllCno(pageable, cNo);
//	}
//
//	@GetMapping("/listBoardByReply/{id}")	//마이페이지 내 댓글 조회
//	@ResponseBody
//	public MyPageDTO listBoardByReply(@PathVariable String id){
//		MyPageDTO mypage = new MyPageDTO();
//		mypage.setBoard(bs.findById(id));
//		mypage.setReply(rs.findByReplyId(id));
//		return mypage;
//	}
//
//	@GetMapping("/listBoard/{id}")	//마이페이지 내 게시물 조회
//	@ResponseBody
//	public List<BoardDTO> listBoard(@PathVariable String id){
//		return bs.findById(id);
//	}
//
//	@GetMapping("/deleteBoard/{bNo}")
//	@ResponseBody
//	public String delete(@PathVariable int bNo) {
//		bs.deleteBorad(bNo);
//		return "";
//	}
//
//
//	@Value("${com.example.upload.path}") // application.properties의 변수
//	private String uploadPath;
//	@PostMapping("/insertBoardWrite")
//	@ResponseBody
////	public String insertBoard(@RequestBody HashMap<String, Object> map) {
//	public String insertBoard(@RequestPart MultipartFile[] uploadFiles, String boardText) {
//
//		System.out.println("-------");
//		System.out.println(uploadFiles);
//		System.out.println(boardText);
////		for (MultipartFile uploadFile : uploadFiles) {
////
/////*			// 이미지 파일만 업로드 가능
////			if(uploadFile.getContentType().startsWith("image") == false){
////				return "이미지만 가능";
////			}*/
////
////			System.out.println(uploadFiles.getClass().getName());
////			System.out.println(uploadFiles[0].getClass().getName());
////
////			// 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
////			String originalName = uploadFile.getOriginalFilename();
////
////			String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
////
////			// 날짜 폴더 생성
////			String folderPath = makeFolder();
////
////			//UUID
////			String uuid = UUID.randomUUID().toString();
////
////			//저장할 파일 이름 중간에 "_"를 이용해 구분
////			String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
////
////			Path savePath = Paths.get(saveName);
////
////			try {
////				uploadFile.transferTo(savePath);
////			}catch (IOException e){
////				e.printStackTrace();
////			}
////		}
//
//		return "OK";
////		bs.saveBoard(b);
//	}
//
//
//	//업로드 파일이 저장 될 날짜 폴더 생성
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
//}
