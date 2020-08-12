package hd.erp.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hd.erp.config.ApplicationYamlRead;
import hd.erp.entity.BoardEntity;
import hd.erp.entity.EmployeeEntity;
import hd.erp.repository.BoardRepository;
import hd.erp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardService {

	@Autowired
	EmployeeRepository employeerepository;
	
	@Autowired
	BoardRepository boardrepository;
	
	@Autowired
	ApplicationYamlRead applicationyamlread;
	
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
	
	//�Խ��� ����Ʈ �̾ƿ���
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

