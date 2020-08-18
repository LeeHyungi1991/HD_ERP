package hd.erp.service;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hd.erp.config.ApplicationYamlRead;
import hd.erp.dto.EmployeeDTO;
import hd.erp.entity.EmployeeEntity;
import hd.erp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DefaultService {

	
	@Autowired
	ApplicationYamlRead applicationyamlread;
	
	@Autowired
	EmployeeService employeeservice;
	
	@Autowired
	EmployeeRepository employeerepository;
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
		return emp.get();
		
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
		
		
		
//		HttpSession session = request.getSession();
//		String r_path=session.getServletContext().getRealPath("/");
//		System.out.println("RPATH="+r_path);
//		String r_path2=session.getServletContext().getRealPath("");
//		System.out.println("RPATH2="+r_path2);
//
//
//		
//		//이미지 이름을 연결
//		String oriFn =mfile.getOriginalFilename(); //업로드 된 이미지 이름
//		
//		System.out.println("FullPath2 : "+r_path+oriFn+"\\upload\\");//실제 이미지가 저장될 경로
//		
//		
//		
//		File Folder2 = new File(r_path+"\\upload\\");
//		
//		
//		 //해당 디렉토리가 없을경우 디렉토리를 생성합니다.
//		if (!Folder2.exists()) {
//			try{
//			    Folder2.mkdir(); //폴더 생성합니다.
//			    System.out.println("폴더가 생성되었습니다.");
//			    System.out.println(Folder2.getPath());
//		        } 
//		        catch(Exception e){
//			    e.getStackTrace();
//			}        
//	         }else {
//			System.out.println("이미 폴더가 생성되어 있습니다.");
//		}
//		
//		
//		File f = new File(Folder2.getPath()+"//"+oriFn);
//		try {
//			mfile.transferTo(f); //스프링의 transferTo를 호출해서 이미지를 저장장소에 복사
//		} catch (IllegalStateException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		
//		System.out.println("rPath : "+r_path);
//		String img_path ="static\\img";
//		System.out.println("imgPath : "+r_path);
//		StringBuffer path= new StringBuffer();
//		path.append(r_path).append(img_path);
//		
//		System.out.println("FullPath : "+path);//실제 이미지가 저장될 경로
//		//파일 업로드 실행
//		
//		File Folder = new File(path.toString());
//		 //해당 디렉토리가 없을경우 디렉토리를 생성합니다.
//		if (!Folder.exists()) {
//			try{
//			    Folder.mkdir(); //폴더 생성합니다.
//			    System.out.println("폴더가 생성되었습니다.");
//			    System.out.println(Folder.getPath());
//		        } 
//		        catch(Exception e){
//			    e.getStackTrace();
//			}        
//	         }else {
//			System.out.println("이미 폴더가 생성되어 있습니다.");
//		}
//		
//		
//		//이미지 이름을 연결
//		String oriFn = "\\"+mfile.getOriginalFilename(); //업로드 된 이미지 이름
//		path.append(oriFn);
//		System.out.println("FullPath2 : "+path);//실제 이미지가 저장될 경로
//		
//		
//		File f = new File(path.toString());
//		try {
//			mfile.transferTo(f); //스프링의 transferTo를 호출해서 이미지를 저장장소에 복사
//		} catch (IllegalStateException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		m.addAttribute("path", path);
	}




	
	
}
