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


//@Slf4j �� �� ��
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

	
	
	
	//���ۼ�
	public void insertbcomment(BcommentEntity bcomment, Long bnum,Long hdcode) {
		EmployeeEntity emp = getemployee(hdcode);//����ۼ��ϴ� ����� emp��ü
		BoardEntity board = getboard(bnum);//�����ִ� �Խ��� ��ü
		//�����ִ°Խ��� ��ü�� ��� �ۼ�
		BcommentEntity newbcomment =new BcommentEntity();
		
		newbcomment.setBcdate(new Date());//�ۼ���
		newbcomment.setBccontent(bcomment.getBccontent());//��۳���
		//newbcomment.setBcreply(-1L);//����
		newbcomment.setBcwriter(emp.getHdname());//�� �ۼ���
		newbcomment.setBoard(board);//��Խ����� ����ΰ�
		newbcomment.setEmployee(emp);//�����ü
		
		bcommentrepository.save(newbcomment);//��� ���
	}
	
	
	//�����
	public void deletebcomment(Long bcnum) {
		bcommentrepository.deleteById(bcnum);
	}
	
	
	//����� ��� ��������
	public BcommentEntity updateget_bcommnet(Long bcnum) {
		Optional<BcommentEntity> bc =bcommentrepository.findById(bcnum);
		return bc.get();
	}
	//����� ���� �����
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
	
	
	//�񸮽�Ʈ
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

		
		
		
		
		
		//return bcommentrepository.findAllByBoardOrderByBcnumAscBcreplyAsc(board);// ��������
		
		
		//return bcommentrepository.findAllByBoardOrderByBcnumAsc(board);
	}

	

	
	
	
	
	
	
	//��� �ۼ�
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
	
	
	
	//id�� ���Ͽ� employee��ü ���
	public EmployeeEntity getemployee(Long hdcode) {
		Optional<EmployeeEntity> emp = employeerepository.findById(hdcode);
		return emp.get();
	}
	//id�� ���Ͽ� Board��ü ���
	public BoardEntity getboard(Long bnum) {
		return boardrepository.findByBnum(bnum);
	}
	//id�� ���Ͽ� bcomment��ü ���
	public BcommentEntity getbcomment(Long bcnum) {
		Optional<BcommentEntity> bcomment = bcommentrepository.findById(bcnum);
		return bcomment.get();
	}
	
	//��ȸ�� �ø���
	public void gohit(BoardEntity board) {
		Long hit = board.getBhit();
		hit++;
		board.setBhit(hit);
		boardrepository.save(board);
	}
	
	
	
	//�Խ��� �۾���
	public void insertboard(BoardEntity board,Long hdcode) {
		log.info("�����ΰ���? {} �Դϴ�",hdcode);
		Optional<EmployeeEntity> emp = employeerepository.findById(hdcode);
		EmployeeEntity employee = emp.get();
		
		board.setEmployee(employee);
		board.setBdate(new Date());
		board.setBhit(0L);
		board.setBlike(0L);
		
		boardrepository.save(board);
		
	}
	
	//�Խ��� ����Ʈ �̾ƿ���(��ü;;�Ⱦ�?)
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
	//�Խ��� ����Ʈ �̾ƿ���(����¡ !��)
	public Page<BoardEntity> boardlistpaging(int nowpage, int size){
		Page<BoardEntity> pagelist = boardrepository.findAll(PageRequest.of(nowpage, size,Sort.by("bnum").descending()));
		
		return pagelist;
	}
	
	//�Խ��� �˻� �ۼ���(����¡��)
	public Page<BoardEntity> searchtype_writer(int nowpage,int size,String searchvalue){
		EmployeeEntity emp = employeerepository.findByHdname(searchvalue);
		List<BoardEntity> board = boardrepository.findAllByEmployee(emp);
		if(emp !=null) {
		System.out.println("�˻��� hdname>>>"+emp.getHdname());
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
	//�Խ��� �˻� ����(����¡��)
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
	//�Խ��� �󼼺���
	public BoardEntity boarddetail(Long bnum) {
		return boardrepository.findByBnum(bnum);
	}
	
	//�Խ��� �����
	public void boarddelete(Long bnum) {
		boardrepository.deleteById(bnum);
	}
	//�Խ��� ����
	public BoardEntity boardupdate(Long bnum) {
		return boardrepository.findByBnum(bnum);
	}
	//�Խ��Ǽ����Ҷ� ������
	public void boardupdatepost(BoardEntity board,Long hdcode) {
		System.out.println(board.getEmployee());
		System.out.println(board.getBtitle());
		log.info("���ƾƾƾƾƾƾ�{},{},{},{},{},{},{}",board.getBcontent(),board.getBdate(),board.getBhit(),board.getBlike(),board.getBnum(),board.getBtitle(),board.getEmployee());
		
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
		//����
		public String summernoteimgupload(MultipartFile file) {
			JSONObject jsonobject=new JSONObject();
			
			String staticpath=applicationyamlread.getPath();
			
			String fileRoot =staticpath+"\\img\\board";
			String originalFileName = file.getOriginalFilename();
			String extension =originalFileName.substring(originalFileName.lastIndexOf("."));
			String savedFileName =UUID.randomUUID()+extension;
			File targetFile = new File(fileRoot);
			
				
				 //�ش� ���丮�� ������� ���丮�� �����մϴ�.
				if (!targetFile.exists()) {
					try{
						targetFile.mkdir(); //���� �����մϴ�.
					    System.out.println("������ �����Ǿ����ϴ�.");
					    System.out.println(targetFile.getPath());
				        } 
				        catch(Exception e){
					    e.getStackTrace();
					}        
			         }else {
					System.out.println("�̹� ������ �����Ǿ� �ֽ��ϴ�.");
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


