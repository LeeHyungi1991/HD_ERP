package hd.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	@GetMapping("/user.board")
	public String board() {
		return"board/board";
	}
	@GetMapping("/user.boarddetail")
	public String boarddetail() {
		return"board/boarddetail";
	}
	@GetMapping("/user.boardwrite")
	public String boardwrite() {
		return"board/boardwrite";
	}
}
