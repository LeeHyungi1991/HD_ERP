 package hd.erp.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hd.erp.config.ApplicationYamlRead;
import hd.erp.dao.BcommentDAO;
import hd.erp.dto.BcommentDTO;
import hd.erp.entity.BcommentEntity;
import hd.erp.entity.BoardEntity;
import hd.erp.entity.EmployeeEntity;
import hd.erp.repository.BcommentRepository;
import hd.erp.repository.BoardRepository;
import hd.erp.repository.EmployeeRepository;


//@Slf4j 롬 복 ㅠ
@Service
public class BoardService {

	
	private static final Logger log = LoggerFactory.getLogger(BoardService.class);

	
	@Autowired
	EmployeeRepository employeerepository;
	
	@Autowired
	BoardRepository boardrepository;
	
	@Autowired
	BcommentRepository bcommentrepository;
	
	@Autowired
	ApplicationYamlRead applicationyamlread;
	
	@Autowired
	EntityManager entitymanager;
	
	@Autowired
	BcommentDAO bcommentdao;

	private List<BcommentEntity> tree;

	
	
	
	//댓작성
	public void insertbcomment(BcommentEntity bcomment, Long bnum,Long hdcode) {
		EmployeeEntity emp = getemployee(hdcode);//댓글작성하는 사람의 emp객체
		BoardEntity board = getboard(bnum);//보고있는 게시판 객체
		//보고있는게시판 객체에 댓글 작성
		BcommentEntity newbcomment =new BcommentEntity();
		
		newbcomment.setBcdate(new Date());//작성일
		newbcomment.setBccontent(bcomment.getBccontent());//댓글내용
		//newbcomment.setBcreply(-1L);//리플
		newbcomment.setBcwriter(emp.getHdname());//댓 작성자
		newbcomment.setBoard(board);//어떤게시판의 댓글인가
		newbcomment.setEmployee(emp);//사원객체
		
		bcommentrepository.save(newbcomment);//댓글 등록
	}
	
	
	//댓삭제
	public void deletebcomment(Long bcnum) {
		bcommentrepository.deleteById(bcnum);
	}
	
	
	//댓수정 댓글 가져오기
	public BcommentEntity updateget_bcommnet(Long bcnum) {
		Optional<BcommentEntity> bc =bcommentrepository.findById(bcnum);
		return bc.get();
	}
	//댓수정 리얼 댓수정
	public void updatebcomment(BcommentEntity bcomment , Long bnum) {
		
//		BoardEntity board= getboard(bnum);
//		bcomment.setBcdate(new Date());
//		bcomment.setBoard(board);
		
		
		
		BoardEntity board= getboard(bnum);
		Optional<BcommentEntity> bcommentopt = bcommentrepository.findById(bcomment.getBcnum());
		BcommentEntity bcommented = bcommentopt.get();
		bcommented.setBcdate(new Date());
		bcommented.setBoard(board);
		bcommented.setBccontent(bcomment.getBccontent());
		
		
		bcommentrepository.save(bcommented);
		
	}
	
	
	//댓리스트
	public List<BcommentEntity> listbcommnet(BoardEntity board) {
		//List<BcommentEntity> bcomments = bcommentrepository.findAllByBoardOrderByBcnumAsc(board);
		List<BcommentEntity> bcomments = bcommentrepository.findAllByBoardOrderByBcnumAsc(board);
		System.out.println("bcomm is >>"+bcomments);
		
		List<BcommentDTO> hierlist = bcommentdao.hierarchical(board.getBnum());
		
		System.out.println("hiersize >>"+hierlist.size());
		List<BcommentEntity> hierlistentity = new ArrayList<BcommentEntity>();
		for(BcommentDTO b : hierlist) {
			BcommentEntity bcentity = new BcommentEntity();
			
			System.out.println("bcnum>>"+b.getBc_num());
			bcentity.setBcnum(b.getBc_num());
			
			System.out.println("bcdate>>"+b.getBc_date());
			bcentity.setBcdate(b.getBc_date());
			
			System.out.println("bccontent >>"+b.getBc_content());
			bcentity.setBccontent(b.getBc_content());
			
			System.out.println("bcdepth>>"+b.getBc_depth());
			bcentity.setBcdepth(b.getBc_depth());
			
			System.out.println("bc_writer>>"+b.getBc_writer());
			bcentity.setBcwriter(b.getBc_writer());
			System.out.println("deptn entity <<"+bcentity.getBcdepth());
			
			System.out.println("board >>"+b.getBoard_b_num());
			bcentity.setBoard(boardrepository.findByBnum(b.getBoard_b_num()));
			
			System.out.println("employee >>"+b.getEmployee_hd_code());
			bcentity.setEmployee(employeerepository.findByhdcode(b.getEmployee_hd_code()).get());
			
			System.out.println("bcreply >>"+b.getBc_reply());
			if(b.getBc_reply() != null) {
			bcentity.setBcreply(bcommentrepository.findById(b.getBc_reply()).get());
			}
			System.out.println("============================");
			hierlistentity.add(bcentity);
			
		}
		
		System.out.println("<><>hierlistentitysize"+hierlistentity.size());
		return hierlistentity;

		
		
		
		
		
		//return bcommentrepository.findAllByBoardOrderByBcnumAscBcreplyAsc(board);// ㄴㄴㄴㄴ
		
		
		//return bcommentrepository.findAllByBoardOrderByBcnumAsc(board);
	}

	

	
	
	
	
	
	
	//대댓 작성
	public void insertreply(BcommentEntity bcomment,Long thisbcnum, Long hdcode, Long bnum) {
		BcommentEntity thisbcomment =getbcomment(thisbcnum);
		EmployeeEntity emp = getemployee(hdcode);
		BoardEntity board = getboard(bnum);
		
		bcomment.setBcdate(new Date());
		bcomment.setBcreply(thisbcomment);
		bcomment.setBoard(board);
		bcomment.setEmployee(emp);
		bcomment.setBcwriter(emp.getHdname());
		
		bcommentrepository.save(bcomment);
		
		
	}
	
	
	
	//id를 통하여 employee객체 얻기
	public EmployeeEntity getemployee(Long hdcode) {
		Optional<EmployeeEntity> emp = employeerepository.findById(hdcode);
		return emp.get();
	}
	//id를 통하여 Board객체 얻기
	public BoardEntity getboard(Long bnum) {
		return boardrepository.findByBnum(bnum);
	}
	//id를 통하여 bcomment객체 얻기
	public BcommentEntity getbcomment(Long bcnum) {
		Optional<BcommentEntity> bcomment = bcommentrepository.findById(bcnum);
		return bcomment.get();
	}
	
	//조회수 올리기
	public void gohit(BoardEntity board) {
		Long hit = board.getBhit();
		hit++;
		board.setBhit(hit);
		boardrepository.save(board);
	}
	
	
	
	//게시판 글쓰기
	public void insertboard(BoardEntity board,Long hdcode) {
		log.info("누구인가요? {} 입니다",hdcode);
		Optional<EmployeeEntity> emp = employeerepository.findById(hdcode);
		EmployeeEntity employee = emp.get();
		
		board.setEmployee(employee);
		board.setBdate(new Date());
		board.setBhit(0L);
		board.setBlike(0L);
		
		boardrepository.save(board);
		
	}
	
	//게시판 리스트 뽑아오기(전체;;안씀?)
	public List<BoardEntity> boardlist(){
		int page =1;
		int size =15;
		Page<BoardEntity> pagelist = boardrepository.findAll(PageRequest.of(page, size,Sort.by("bnum").descending()));
		System.out.println(pagelist.toString());
		System.out.println("totalcount?"+pagelist.getTotalElements());
		List<BoardEntity> board =pagelist.getContent();
		for(BoardEntity e : board) {
			System.out.println(e.getBtitle());
		}
		return boardrepository.findAllByOrderByBnumDesc();
	}
	//게시판 리스트 뽑아오기(페이징 !씀)
	public Page<BoardEntity> boardlistpaging(int nowpage, int size){
		Page<BoardEntity> pagelist = boardrepository.findAll(PageRequest.of(nowpage, size,Sort.by("bnum").descending()));
		
		return pagelist;
	}
	
	//게시판 검색 작성자(페이징씀)
	public Page<BoardEntity> searchtype_writer(int nowpage,int size,String searchvalue){
		EmployeeEntity emp = employeerepository.findByHdname(searchvalue);
		List<BoardEntity> board = boardrepository.findAllByEmployee(emp);
		if(emp !=null) {
		System.out.println("검색한 hdname>>>"+emp.getHdname());
		}
		if(board!=null) {
			for(BoardEntity e : board) {
				System.out.println(e.getBtitle());
			}
		}
		Page<BoardEntity> boardpage = boardrepository.findAllByEmployee(emp,PageRequest.of(nowpage, size));
		if(boardpage !=null) {
			return boardpage;
		}else {
			return null;
		}
	}
	//게시판 검색 제목(페이징씀)
		public Page<BoardEntity> searchtype_title(int nowpage,int size,String searchvalue){
			StringBuffer sb =new StringBuffer();
			sb.append("%");
			sb.append(searchvalue);
			sb.append("%");
			System.out.println("sb.tostring>>"+sb.toString());
			searchvalue=sb.toString();
			
			Page<BoardEntity> boardpage = boardrepository.findAllByBtitleLike(searchvalue, PageRequest.of(nowpage, size));
			if(boardpage !=null) {
				return boardpage;
			}else {
				return null;
			}
		}
	//게시판 상세보기
	public BoardEntity boarddetail(Long bnum) {
		return boardrepository.findByBnum(bnum);
	}
	
	//게시판 지우기
	public void boarddelete(Long bnum) {
		boardrepository.deleteById(bnum);
	}
	//게시판 수정
	public BoardEntity boardupdate(Long bnum) {
		return boardrepository.findByBnum(bnum);
	}
	//게시판수정할때 폼전송
	public void boardupdatepost(BoardEntity board,Long hdcode) {
		System.out.println(board.getEmployee());
		System.out.println(board.getBtitle());
		log.info("으아아아아아아아{},{},{},{},{},{},{}",board.getBcontent(),board.getBdate(),board.getBhit(),board.getBlike(),board.getBnum(),board.getBtitle(),board.getEmployee());
		
		BoardEntity upboard = new BoardEntity();
		Optional<EmployeeEntity> employeeopt = employeerepository.findByhdcode(hdcode);
		EmployeeEntity employee = employeeopt.get();
		
		upboard.setBnum(board.getBnum());
		upboard.setBcontent(board.getBcontent());
		upboard.setBdate(new Date());
		upboard.setBhit(board.getBhit());
		upboard.setBlike(board.getBlike());
		upboard.setBtitle(board.getBtitle());
		upboard.setEmployee(employee);
		
		boardrepository.save(upboard);
		
		
		
	}
	
	
	//https://programmer93.tistory.com/31
		//ㅅㅂ
		public String summernoteimgupload(MultipartFile file) {
			JSONObject jsonobject=new JSONObject();
			
			String staticpath=applicationyamlread.getPath();
			
			String fileRoot =staticpath+"\\img\\board";
			String originalFileName = file.getOriginalFilename();
			String extension =originalFileName.substring(originalFileName.lastIndexOf("."));
			String savedFileName =UUID.randomUUID()+extension;
			File targetFile = new File(fileRoot);
			
				
				 //해당 디렉토리가 없을경우 디렉토리를 생성합니다.
				if (!targetFile.exists()) {
					try{
						targetFile.mkdir(); //폴더 생성합니다.
					    System.out.println("폴더가 생성되었습니다.");
					    System.out.println(targetFile.getPath());
				        } 
				        catch(Exception e){
					    e.getStackTrace();
					}        
			         }else {
					System.out.println("이미 폴더가 생성되어 있습니다.");
				}
				
				
				
				
				File targetFile2 = new File(fileRoot);
				try {
					file.transferTo(new File(targetFile2.getPath()+"\\"+savedFileName));
					jsonobject.put("url", "/board/"+savedFileName);
					jsonobject.put("responseCode", "success");
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					jsonobject.put("responseCode", "error");
					e.printStackTrace();
				}
				
		
				
				
			System.out.println(jsonobject);
			return jsonobject.toString();
		}
	
}


