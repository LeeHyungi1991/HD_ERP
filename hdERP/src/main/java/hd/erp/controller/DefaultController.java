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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import hd.erp.config.ApplicationYamlRead;
import hd.erp.dto.EmployeeDTO;
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
		return "index";
	}
	
	@GetMapping("/user/loginsuccess")
	public String loginsuccess() {
		return"index";
	}
	@GetMapping("/admin.register")
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
	
	
	
	@GetMapping("/user.profile")
	public String profile(Model m,Principal principal) {
		m.addAttribute("asdf", "asdf123");
		//String path = "C:\\ikosmo64\\spring\\realerp\\hdERP\\src\\main\\resources\\static\\img\\"+principal.getName(); //���� ���
		String path2 ="img\\"+principal.getName();
		m.addAttribute("path", path2.toString()+"\\"+"profile.png");
		m.addAttribute("sigpath", path2.toString()+"\\"+"signature.png");
		return"userprofile";
	}
	@PostMapping("/user.profile")
	public String postprofile(HttpServletRequest request,@RequestParam("filename")MultipartFile mfile,Principal principal,Model m) {
		
		String path = defaultservice.userprofileimgupload(mfile, principal);
		
		m.addAttribute("path", path);
	
		return"redirect:/user.profile";
		
	}
	
	@PostMapping("/user.profilesigimg")
	public String saveIamge(@RequestParam(value="file", required=true) MultipartFile [] file,Principal principal) {
	    
	    log.debug("file size : ", file[0].getSize());	// ������ ������ ������
	    System.out.println("��������������");
	    System.out.println(file);
	    
	    
	    String statipath = applicationyamlread.getPath();
		
		String path = statipath+"\\img\\"+principal.getName(); //���� ���
		File Folder = new File(path);
		
		
		 //�ش� ���丮�� ������� ���丮�� �����մϴ�.
		if (!Folder.exists()) {
			try{
			    Folder.mkdir(); //���� �����մϴ�.
			    System.out.println("������ �����Ǿ����ϴ�.");
			    System.out.println(Folder.getPath());
		        } 
		        catch(Exception e){
			    e.getStackTrace();
			}        
	         }else {
			System.out.println("�̹� ������ �����Ǿ� �ֽ��ϴ�.");
		}
		
		
		
		
		
		try {
			file[0].transferTo(new File(path+"\\"+"signature.png"));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    
	    return"redirect:/user.profile";
	}
}
