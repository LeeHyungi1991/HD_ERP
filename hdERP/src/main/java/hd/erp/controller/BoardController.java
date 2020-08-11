package hd.erp.controller;

import java.io.File;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.core.util.FileUtil;
import hd.erp.entity.BoardEntity;
import hd.erp.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardservice;
	
	@GetMapping("/user.board")
	public String board(Model m) {
		List<BoardEntity> board = boardservice.boardlist();
		System.out.println(board.size());
		
		m.addAttribute("boardlist", board);
		return"board/board";
	}
	@GetMapping("/user.boarddetail")
	public String boarddetail(Long bnum,Model m) {
		BoardEntity board = boardservice.boarddetail(bnum);
		m.addAttribute("board", board);
		return"board/boarddetail";
	}
	@GetMapping("/user.boardwrite")
	public String boardwrite() {
		return"board/boardwrite";
	}
	@PostMapping("/user.boardwrite")
	public String boardwritepost(BoardEntity board,Principal principal) {
		System.out.println(board.getBtitle());
		System.out.println(board.getBcontent());
		
		boardservice.insertboard(board, Long.parseLong(principal.getName()));
		
		
		return "redirect:/user.board";
	}
	
	
	//https://programmer93.tistory.com/31
//	@PostMapping(value = "/user.boardwriteimg",produces = "application/json")
//	@ResponseBody
//	public JSONObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile file){
//		JSONObject jsonObject = new JSONObject();
//		String fileRoot ="";
//		String originalFileName = file.getOriginalFilename();
//		String extension =originalFileName.substring(originalFileName.lastIndexOf("."));
//		String savedFileName =UUID.randomUUID()+extension;
//		File targetFile = new File(fileRoot+savedFileName);
//		
//		return null;
//	}
//	
	
}
