package hd.erp.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hd.erp.config.ApplicationYamlRead;
import hd.erp.entity.DocumentEntity;
import hd.erp.entity.EmployeeEntity;
import hd.erp.repository.DocumentRepository;
import hd.erp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeManageService {
	
	@Autowired
	ApplicationYamlRead applicationyamlread;
	
	@Autowired
	EmployeeRepository employeerepository;
	
	@Autowired
	DocumentRepository documentrepository;
	
	//기안하기 할때 셀렉트 박스에 들어가는 사람들
	public Map<String, List<EmployeeEntity>> emplist() {
		Map<String, List<EmployeeEntity>> emplist = new HashMap<String, List<EmployeeEntity>>();
		List<EmployeeEntity> adminemplist = employeerepository.findByhdlevel("ADMIN");
		List<EmployeeEntity> memberemplist = employeerepository.findByhdlevel("MEMBER");
		List<EmployeeEntity> useremplist = employeerepository.findByhdlevel("USER");
		System.out.println("adminemplist >"+adminemplist.size());
		System.out.println("memberemplist >"+memberemplist.size());
		System.out.println("useremplist >"+useremplist.size());
		emplist.put("adminlist", adminemplist);
		emplist.put("memberlist", memberemplist);
		emplist.put("userlist", useremplist);
		return emplist;
		
	}
	//내 정보가져오기 기안하기에 씀
	public EmployeeEntity getmylevel(Long hd_code) {
		Optional<EmployeeEntity> empopt = employeerepository.findByhdcode(hd_code);
		EmployeeEntity emp = empopt.get();
		String mylevel = emp.getHdlevel();
		System.out.println("mylevel >>"+mylevel);
		
		return emp;
	}
	
	//기안하기 폼전송 저장
	public void documentinsert(DocumentEntity document, String emp1,String emp2, String emp3, String mynum) {
		System.out.println("시작");
		DocumentEntity doc = new DocumentEntity();
		EmployeeEntity appemp1 =null;
		EmployeeEntity appemp2=null;
		EmployeeEntity appemp3=null;
		if(emp1 != null) {
		appemp1 = employeerepository.findByhdcode(Long.parseLong(emp1)).get();
		}
		if(emp2 != null){
		appemp2 = employeerepository.findByhdcode(Long.parseLong(emp2)).get();
		}
		if(emp3 !=null) {
		appemp3 = employeerepository.findByhdcode(Long.parseLong(emp3)).get();
		}
		System.out.println("시작123");
		EmployeeEntity myemp = employeerepository.findByhdcode(Long.parseLong(mynum)).get();
		System.out.println("--=-=-=-==-");
		doc.setDocdate(new Date());
		doc.setDocfirstemp(appemp1);
		doc.setDocsecondemp(appemp2);
		doc.setDocthirdemp(appemp3);
		doc.setDocdrafter(myemp);
		System.out.println(myemp);
		if(myemp.getHdlevel().equals("ADMIN")) {
			doc.setDocstatus(2);
		}else if(myemp.getHdlevel().equals("MEMBER")) {
			doc.setDocstatus(1);
		}else if(myemp.getHdlevel().equals("USER")) {
			doc.setDocstatus(0);
		}else {
			doc.setDocstatus(0);
		}
		
		
		doc.setDoctitle(document.getDoctitle());
		doc.setDoccontent(document.getDoccontent());
		documentrepository.save(doc);
		System.out.println("서류 저장");
		log.info("서류 저장{}",doc);

	}
	
	//서류관리 들어갔을때 진행중서류,완료서류,기각서류 보기위함
	public Map<String, List<DocumentEntity>> godocmanage(){
		
		Map<String, List<DocumentEntity>> mydoclists =new HashMap<>();
		List<DocumentEntity> completedoc = documentrepository.findBydocstatus(3);
		List<DocumentEntity> ignoredoc = documentrepository.findBydocstatus(-1);
		List<DocumentEntity> ingdoc = documentrepository.findBydocstatusBetween(0, 2);
		
		mydoclists.put("completedoc", completedoc);
		mydoclists.put("ignoredoc",ignoredoc);
		mydoclists.put("ingdoc", ingdoc);
		System.out.println("completedoc"+completedoc.size());
		System.out.println("ignoredoc"+ignoredoc.size());
		System.out.println("ingdoc"+ingdoc.size());
		
		return mydoclists;
	}
	
	
	
	//https://programmer93.tistory.com/31
		//ㅅㅂ
		public String summernoteimgupload(MultipartFile file) {
			JSONObject jsonobject=new JSONObject();
			
			String staticpath=applicationyamlread.getPath();
			
			String fileRoot =staticpath+"\\img\\document";
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
					jsonobject.put("url", "/document/"+savedFileName);
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

