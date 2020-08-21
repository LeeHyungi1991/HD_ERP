package hd.erp.service;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hd.erp.config.ApplicationYamlRead;
import hd.erp.dto.EmployeeDTO;
import hd.erp.entity.BoardEntity;
import hd.erp.entity.DocumentEntity;
import hd.erp.entity.EmployeeEntity;
import hd.erp.repository.BoardRepository;
import hd.erp.repository.DocumentRepository;
import hd.erp.repository.EmployeeRepository;


//@Slf4j
@Service
public class DefaultService {

	
	private static final Logger log = LoggerFactory.getLogger(DefaultService.class);

	
	@Autowired
	ApplicationYamlRead applicationyamlread;
	
	@Autowired
	EmployeeService employeeservice;
	
	@Autowired
	EmployeeRepository employeerepository;
	
	@Autowired
	BoardRepository boardrepository;
	
	@Autowired
	DocumentRepository documenetrespository;
	
	//회원가입
	public void register(EmployeeDTO employeedto) {
		employeeservice.register(employeedto);
	}
	
	
	
	
	public EmployeeEntity findindexname(Long hdcode) {
		Optional<EmployeeEntity> emp = employeerepository.findById(hdcode);
		return emp.get();
	}
	
	
	
	
	
	public EmployeeEntity getuserprofile(Long hd_code) {
		Optional<EmployeeEntity> emp = employeerepository.findByhdcode(hd_code);
		EmployeeEntity testemp = emp.get();
		System.out.println("employee 가입일 (er) >>"+testemp.getHdindate());
		System.out.println("employee 생일(er) >>"+testemp.getHdbirth());
		
		testemp.setHdindate(testemp.getHdindate().substring(0, 10));
		testemp.setHdbirth(testemp.getHdbirth().substring(0, 10));
		System.out.println("resolve >> "+testemp.getHdindate().substring(0, 10));
		System.out.println("resolve >> "+testemp.getHdbirth().substring(0, 10));
		
		
		return testemp;
		
	}
	
	
	//유저프로필 수정
	public void uploadprofile(EmployeeEntity employee,Long hdcode) {
		System.out.println(">>>>>>>>>>>>>>>");
		System.out.println(employee.getHdcode());
		System.out.println(employee.getHdadminpw());
		System.out.println(employee.getHdpw());
		System.out.println(employee.getHdlevel());
		System.out.println("-------------------");
		System.out.println(employee.getHdbirth());
		System.out.println(employee.getHdphn());
		System.out.println(employee.getHdloc());
		System.out.println(employee.getHdemail());
		System.out.println(employee.getHdgender());
		System.out.println(employee.getHdname());
		System.out.println(employee.getHddname());
		System.out.println(employee.getHdindate());
		
		
		System.out.println("<<<<<<<<<<<<<<<<<");
		
		EmployeeEntity myemp = employeerepository.findByhdcode(hdcode).get();
		myemp.setHdbirth(employee.getHdbirth());
		myemp.setHdphn(employee.getHdphn());
		myemp.setHdloc(employee.getHdloc());
		myemp.setHdemail(employee.getHdemail());
		myemp.setHdgender(employee.getHdgender());
		myemp.setHdname(employee.getHdname());
		myemp.setHddname(employee.getHddname());
		myemp.setHdindate(employee.getHdindate());
		
		employeerepository.save(myemp);
		
//		employeerepository.save(employee);
		
	}
	
	
	
	
	
	
	public String userprofileimgupload(MultipartFile mfile,Principal principal) {
//		System.out.println(mfile.getName());
//		System.out.println(mfile.getContentType());
//		System.out.println(mfile.getOriginalFilename());
//		System.out.println(mfile.getSize());
		
		String statipath = applicationyamlread.getPath();
		
		String path = statipath+"\\img\\"+principal.getName(); //폴더 경로
		File Folder = new File(path);
		
		
		 //해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		if (!Folder.exists()) {
			try{
			    Folder.mkdir(); //폴더 생성합니다.
			    System.out.println("폴더가 생성되었습니다.");
			    System.out.println(Folder.getPath());
		        } 
		        catch(Exception e){
			    e.getStackTrace();
			}        
	         }else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}
		
		
		
		
		if(mfile != null) {
			try {
				mfile.transferTo(new File(path+"\\"+"profile.png"));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.info("path는 {}",path.toString()+"\\"+"profile.png");
		
		return path.toString()+"\\"+"profile.png";
		
		
		

	}



	//최근 서류가져오기
	public List<DocumentEntity> getrecentdocument() {
		Page<DocumentEntity> recentdoc = documenetrespository.findBydocstatus(3, (PageRequest.of(0, 3,Sort.by("docnum").descending())));
		return recentdoc.getContent();
	}



	//최근 게시판 가져오기
	public List<BoardEntity> getrecentboardlist() {
		Page<BoardEntity> recentboard= boardrepository.findAll(PageRequest.of(0, 3,Sort.by("bnum").descending()));
		return recentboard.getContent();
	}



	
	//로그인 정보 있는지 가져오기
	public Long usercheck(long hdcode) {
		
		//Long e = employeerepository.cou
		Long cnt =employeerepository.countByhdcode(hdcode);
		
		return cnt;
	}




	public List<EmployeeEntity> getemplist() {
		List<EmployeeEntity> emplist = employeerepository.findAll();
		System.out.println("emplist >>> "+ emplist);
		return emplist;
	}

	
	

	



	
	
}
