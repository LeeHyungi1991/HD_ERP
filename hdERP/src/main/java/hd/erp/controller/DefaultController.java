package hd.erp.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import hd.erp.dto.EmployeeDTO;
import hd.erp.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DefaultController {
	@Autowired
	EmployeeService employeeservice;
	@GetMapping("/")
	public String firstaccess() {
		return "login";
	}
	
	@GetMapping(value = {"/user.index","/admin.index","/member.index","/asdf","/qwer/asdf","/admin.index.as"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/user/loginsuccess")
	public String loginsuccess() {
		return"index";
	}
	@GetMapping("/register")
	public String register() {
		return"register";
	}
	@PostMapping("/register")
	public String execjoin(EmployeeDTO employeedto) {
		employeeservice.register(employeedto);
		return"redirect:/login";
	}
	
	
	@GetMapping("/logout/reuslt")
	public String logout() {
		return"logout";
	}
	
	
//	@GetMapping("/user/index")
//	public String uindex() {
//		return"index";
//	}
	
	@GetMapping("/denied")
	public String denied() {
		return "denied";
	}
	@GetMapping("/login")
	public String login2() {
		return "login";
	}
	@PostMapping("/login")
	public String loginpost() {
		System.out.println("asdf");
		return "index";
	}
	
	
	
	@GetMapping("/user.profile")
	public String profile(Model m,Principal principal) {
		m.addAttribute("asdf", "asdf123");
		String path = "C:\\ikosmo64\\spring\\realerp\\hdERP\\src\\main\\resources\\static\\img\\"+principal.getName(); //폴더 경로
		String path2 ="img\\"+principal.getName();
		m.addAttribute("path", path2.toString()+"\\"+"profile.png");
		return"userprofile";
	}
	@PostMapping("/user.profile")
	public String postprofile(HttpServletRequest request,@RequestParam("filename")MultipartFile mfile,Principal principal,Model m) {
		
		System.out.println(mfile.getName());
		System.out.println(mfile.getContentType());
		System.out.println(mfile.getOriginalFilename());
		System.out.println(mfile.getSize());
		
		String path = "C:\\ikosmo64\\spring\\realerp\\hdERP\\src\\main\\resources\\static\\img\\"+principal.getName(); //폴더 경로
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
		
		
		
		
		
		try {
			mfile.transferTo(new File(path+"\\"+"profile.png"));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("path는 {}",path.toString()+"\\"+"profile.png");
		m.addAttribute("path", path.toString()+"\\"+"profile.png");
		
		
		
		
		HttpSession session = request.getSession();
		String r_path=session.getServletContext().getRealPath("/");
		System.out.println("RPATH="+r_path);
		String r_path2=session.getServletContext().getRealPath("");
		System.out.println("RPATH2="+r_path2);


		
		//이미지 이름을 연결
		String oriFn =mfile.getOriginalFilename(); //업로드 된 이미지 이름
		
		System.out.println("FullPath2 : "+r_path+oriFn+"\\upload\\");//실제 이미지가 저장될 경로
		
		
		
		File Folder2 = new File(r_path+"\\upload\\");
		
		
		 //해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		if (!Folder2.exists()) {
			try{
			    Folder2.mkdir(); //폴더 생성합니다.
			    System.out.println("폴더가 생성되었습니다.");
			    System.out.println(Folder2.getPath());
		        } 
		        catch(Exception e){
			    e.getStackTrace();
			}        
	         }else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}
		
		
		File f = new File(Folder2.getPath()+"//"+oriFn);
		try {
			mfile.transferTo(f); //스프링의 transferTo를 호출해서 이미지를 저장장소에 복사
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
		return"redirect:/user.profile";
		//return"/user.profile";
	}
}
