package hd.erp.controller;


import java.security.Principal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import hd.erp.entity.BcommentEntity;
import hd.erp.entity.BoardEntity;

import hd.erp.service.BoardService;

@Controller
@JsonComponent
public class BoardController {
	
	@Autowired
	BoardService boardservice;
	
	//그냥 게시판//nowpage 는 보고싶은 페이지
	@GetMapping("/user.board")
	public String board(Model m,int nowpage,HttpServletRequest req
			,@RequestParam(required = false,name = "searchtype")String searchtype
			,@RequestParam(required = false,name = "searchvalue")String searchvalue
			) {
		
		
		HttpSession session = req.getSession();
		session.setAttribute("nowpage",nowpage );
		session.setAttribute("searchtype", searchtype);
		int size = 15;
		
		if(searchtype != null) {
			if(searchtype.equals("writer")) {
			System.out.println("searchvalue >>"+searchvalue);
			
			Page<BoardEntity> pagelist =boardservice.searchtype_writer(nowpage, size, searchvalue);
			List<BoardEntity> boardpage = pagelist.getContent();
			m.addAttribute("totalcount", pagelist.getTotalElements());
			m.addAttribute("size", size);
			m.addAttribute("boardlist", boardpage);
			m.addAttribute("nowpage", nowpage);
			
			return "board/board";
			}else if(searchtype.equals("title")) {
				System.out.println("타이틀로옴");
				Page<BoardEntity> pagelist =boardservice.searchtype_title(nowpage, size, searchvalue);
				List<BoardEntity> boardpage = pagelist.getContent();
				m.addAttribute("totalcount", pagelist.getTotalElements());
				m.addAttribute("size", size);
				m.addAttribute("boardlist", boardpage);
				m.addAttribute("nowpage", nowpage);
				return"board/board";
			}
		}
		
		System.out.println("session nowpage>>>>"+session.getAttribute("nowpage"));
		//List<BoardEntity> board = boardservice.boardlist();
		//System.out.println(board.size());
		
		Page<BoardEntity> pagelist =boardservice.boardlistpaging(nowpage, size);
		List<BoardEntity> boardpage = pagelist.getContent();
		
		m.addAttribute("totalcount", pagelist.getTotalElements());
		m.addAttribute("size", size);
		m.addAttribute("boardlist", boardpage);
		m.addAttribute("nowpage", nowpage);
		return"board/board";
	}
	
	//게시판 상세보기 그냥 보기
	@GetMapping("/user.boarddetail")
	public String boarddetail(Long bnum,Model m,Principal principal,HttpServletRequest req) {
		
		
		//이건 게시판 컨텐츠 가져오는것
		BoardEntity board = boardservice.boarddetail(bnum);
		m.addAttribute("board", board);
		//이건 게시판 댓글 가져오는것
		List<BcommentEntity> bcomments =boardservice.listbcommnet(board);
		m.addAttribute("bcomments", bcomments);
		
		HttpSession session = req.getSession();
		int nowpage = (int) session.getAttribute("nowpage");
		
		System.out.println("nowpage= "+nowpage);
		m.addAttribute("nowpage", nowpage);
		
		
		
		m.addAttribute("writer", board.getEmployee().getHdcode());
		m.addAttribute("who", principal.getName());
		
		
		//조회수올리기
		boardservice.gohit(board);
		
		return"board/boarddetail";
	}
	
	//게시판 상세보기 댓글 폼 전송 즉 댓글 작성//
	@PostMapping("/user.boarddetail")
	public String boarddetailpost(Long bnum,Model m,BcommentEntity bcomment,Principal principal,HttpServletRequest req) {
		//댓글 작성
		HttpSession session = req.getSession();
		int nowpage = (int) session.getAttribute("nowpage");
		System.out.println("nowpage= "+nowpage);
		m.addAttribute("nowpage", nowpage);
				
		boardservice.insertbcomment(bcomment, bnum, Long.parseLong(principal.getName()));
		return"redirect:/user.boarddetail?bnum="+bnum;
	}
	//게시판 댓글 삭제
	@PostMapping("/user.bcommnetdelete")
	public String bcommentdelete(Long bcnum,Long bnum) {
		System.out.println("bcnum"+bcnum);
		System.out.println("bnum"+bnum);
		boardservice.deletebcomment(bcnum);
		return"redirect:/user.boarddetail?bnum="+bnum;
	}
	
	//게시판 댓글 수정 모달 클릭시
	@PostMapping("/user.bcommentupdate_modal")
	@ResponseBody
	public String bcommentupdatemodal(Long bcnum){
		System.out.println("bcnum = >>" +bcnum);
		BcommentEntity board = boardservice.updateget_bcommnet(bcnum);
		String bccontent=board.getBccontent();
		System.out.println("bccontent >" +bccontent);
		JSONObject boardjson =new JSONObject();
		boardjson.put("bccontent", board.getBccontent());
		boardjson.put("bcnum", board.getBcnum());
		boardjson.put("bcwriter",board.getBcwriter());
		boardjson.put("bcreply",board.getBcreply());
		return boardjson.toString();
	}
	//게시판 댓글 수정
	@PostMapping("/user.bcommentupdate")
	public String bcommentupdate(BcommentEntity bcomment,Long bnum) {
		System.out.println(bcomment);
		System.out.println("asdfbnum >>"+bnum);
		boardservice.updatebcomment(bcomment, bnum);
		
		
		return "redirect:/user.boarddetail?bnum="+bnum;
	}
	
	//게시판 대댓 작성
	@PostMapping("/user.bcommentreply")
	public String bcommentreply(BcommentEntity bcomment,Long bnum,Long thisbcnum,Principal principal) {
		
		System.out.println("thisbcnum ="+thisbcnum);
		boardservice.insertreply(bcomment, thisbcnum, Long.parseLong(principal.getName()), bnum);
		
		return "redirect:/user.boarddetail?bnum="+bnum;
	}
	
	
	//게시판쓰기
	@GetMapping("/user.boardwrite")
	public String boardwrite() {
		return"board/boardwrite";
	}
	
	//게시판쓰기 폼전송
	@PostMapping("/user.boardwrite")
	public String boardwritepost(BoardEntity board,Principal principal,HttpServletRequest req) {
		System.out.println(board.getBtitle());
		System.out.println(board.getBcontent());
		
		HttpSession session=req.getSession();
		boardservice.insertboard(board, Long.parseLong(principal.getName()));
		
		
		return "redirect:/user.board?nowpage="+session.getAttribute("nowpage");
	}
	
	
	//게시판 쓰기 이미지 처리
	@PostMapping(value = "/user.boardwriteimg",produces = "application/json")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile file){
		return boardservice.summernoteimgupload(file);
	}
	
	//게시판 지우기
	@GetMapping(value = "/user.boarddelete")
	public String boarddelete(Long bnum,HttpServletRequest req) {
		HttpSession session=req.getSession();
		boardservice.boarddelete(bnum);
		return "redirect:/user.board?nowpage="+session.getAttribute("nowpage");
	}
	
	
	//게시판 업데이트
	@GetMapping(value = "/user.boardupdate")
	public String boardupdate(Long bnum,Model m) {
		BoardEntity board= boardservice.boardupdate(bnum);
		m.addAttribute("board", board);
		return "board/boardupdate";
	}
	
	//게시판 업데이트 폼전송
	@PostMapping(value = "/user.boardupdate")
	public String boardupatepost(BoardEntity board,Long hdcode) {
		boardservice.boardupdatepost(board,hdcode);
		System.out.println("hdcode = "+hdcode);
		Long bnum=board.getBnum();
		System.out.println(bnum);
		return "redirect:/user.boarddetail?bnum="+bnum;
	}
}
