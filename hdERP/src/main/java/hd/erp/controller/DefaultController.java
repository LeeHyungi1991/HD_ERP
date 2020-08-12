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
import hd.erp.service.DefaultService;
import hd.erp.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DefaultController {
	
	@Autowired
	DefaultService defaultservice;
	
	
	
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
		//String path = "C:\\ikosmo64\\spring\\realerp\\hdERP\\src\\main\\resources\\static\\img\\"+principal.getName(); //폴더 경로
		String path2 ="img\\"+principal.getName();
		m.addAttribute("path", path2.toString()+"\\"+"profile.png");
		return"userprofile";
	}
	@PostMapping("/user.profile")
	public String postprofile(HttpServletRequest request,@RequestParam("filename")MultipartFile mfile,Principal principal,Model m) {
		
		String path = defaultservice.userprofileimgupload(mfile, principal);
		
		m.addAttribute("path", path);
	
		return"redirect:/user.profile";
		
	}
}
