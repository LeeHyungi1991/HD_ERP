package hd.erp.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



import hd.erp.entity.BoardEntity;
import hd.erp.service.BoardService;

@Controller
@JsonComponent
public class BoardController {
	
	@Autowired
	BoardService boardservice;
	
	//�Խ���
	@GetMapping("/user.board")
	public String board(Model m) {
		List<BoardEntity> board = boardservice.boardlist();
		System.out.println(board.size());
		
		
		m.addAttribute("totalcount", 188);
		m.addAttribute("boardlist", board);
		return"board/board";
	}
	
	//�Խ��� �󼼺���
	@GetMapping("/user.boarddetail")
	public String boarddetail(Long bnum,Model m) {
		BoardEntity board = boardservice.boarddetail(bnum);
		m.addAttribute("board", board);
		return"board/boarddetail";
	}
	
	//�Խ��Ǿ���
	@GetMapping("/user.boardwrite")
	public String boardwrite() {
		return"board/boardwrite";
	}
	
	//�Խ��Ǿ��� ������
	@PostMapping("/user.boardwrite")
	public String boardwritepost(BoardEntity board,Principal principal) {
		System.out.println(board.getBtitle());
		System.out.println(board.getBcontent());
		
		boardservice.insertboard(board, Long.parseLong(principal.getName()));
		
		
		return "redirect:/user.board";
	}
	
	
	//�Խ��� ���� �̹��� ó��
	@PostMapping(value = "/user.boardwriteimg",produces = "application/json")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile file){
		return boardservice.summernoteimgupload(file);
	}
	
	//�Խ��� �����
	@GetMapping(value = "/user.boarddelete")
	public String boarddelete(Long bnum) {
		boardservice.boarddelete(bnum);
		return "redirect:/user.board";
	}
	
	
	//�Խ��� ������Ʈ
	@GetMapping(value = "/user.boardupdate")
	public String boardupdate(Long bnum,Model m) {
		BoardEntity board= boardservice.boardupdate(bnum);
		m.addAttribute("board", board);
		return "board/boardupdate";
	}
	
	//�Խ��� ������Ʈ ������
	@PostMapping(value = "/user.boardupdate")
	public String boardupatepost(BoardEntity board,Long hdcode) {
		boardservice.boardupdatepost(board,hdcode);
		System.out.println("hdcode = "+hdcode);
		Long bnum=board.getBnum();
		System.out.println(bnum);
		return "redirect:/user.boarddetail?bnum="+bnum;
	}
}
