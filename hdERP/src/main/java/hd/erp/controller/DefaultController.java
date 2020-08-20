package hd.erp.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import hd.erp.config.ApplicationYamlRead;
import hd.erp.dto.EmployeeDTO;
import hd.erp.entity.BoardEntity;
import hd.erp.entity.DocumentEntity;
import hd.erp.entity.EmployeeEntity;
import hd.erp.service.DefaultService;
import hd.erp.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DefaultController {
	
	@Autowired
	DefaultService defaultservice;
	
	@Autowired
	ApplicationYamlRead applicationyamlread;
	
	@GetMapping("/")
	public String firstaccess() {
		return "login";
	}
	
	@GetMapping(value = {"/user.index","/admin.index","/member.index","/asdf","/qwer/asdf","/admin.index.as"})
	public String index(Principal principal,Model m) {
		EmployeeEntity emp = defaultservice.findindexname(Long.parseLong(principal.getName()));
		m.addAttribute("emp", emp);
		
		//index 에서 최근 게시판 가져오기, 최근서류 가져오기
		List<BoardEntity> recentboard =defaultservice.getrecentboardlist();
		List<DocumentEntity> recentdocument = defaultservice.getrecentdocument();
		m.addAttribute("recentboards",recentboard);
		m.addAttribute("recentdocs",recentdocument);
		
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
		//employeeservice.register(employeedto);
		defaultservice.register(employeedto);
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
	
	
	//프로필페이지보기
	@GetMapping("/user.profile")
	public String profile(Model m,Principal principal) {
		EmployeeEntity emp = defaultservice.getuserprofile(Long.parseLong(principal.getName()));
		
		String path2 ="img\\"+principal.getName();
		m.addAttribute("path", path2.toString()+"\\"+"profile.png");
		m.addAttribute("sigpath", path2.toString()+"\\"+"signature.png");
		m.addAttribute("emp", emp);
		return"userprofile";
	}
	//프로필이미지,사원정보 업로드
	@PostMapping("/user.profile")
	public String postprofile(HttpServletRequest request,@RequestParam(value = "filename",required = false)MultipartFile mfile,Principal principal,Model m,EmployeeEntity employee) {
		//이미지 업로드
		String path = defaultservice.userprofileimgupload(mfile, principal);
		
		//프로필업로드
		defaultservice.uploadprofile(employee,Long.parseLong(principal.getName()));
		//
		System.out.println("pro up emp >> "+employee.toString());
		m.addAttribute("path", path);
	
		return"redirect:/user.profile";
		
	}
	
	//사인이미지
	@PostMapping("/user.profilesigimg")
	public String saveIamge(@RequestParam(value="file", required=true) MultipartFile [] file,Principal principal) {
	    
	    log.debug("file size : ", file[0].getSize());	// 서버로 무사히 안착됨
	    System.out.println("ㅁㄴㅇㄻㄴㅇㄹ");
	    System.out.println(file);
	    
	    
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
		
		
		
		
		
		try {
			file[0].transferTo(new File(path+"\\"+"signature.png"));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    
	    return"redirect:/user.profile";
	}
	
	
	
	
	//로그인 정보 있는지 가져오기
	@PostMapping("/user.usercheck")
	@ResponseBody
	public String usercheck(String id) {
		
		Long chk = defaultservice.usercheck(Long.parseLong(id));
		
		System.out.println("chk = "+chk);
		
		if(chk >0) {
			return "ok";
		}else {
			return "no";
		}
		
		
	}
	
	
	
}
