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

@Controller
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
	public String profile(Model m) {
		m.addAttribute("asdf", "asdf123");
		return"userprofile";
	}
	@PostMapping("/user.profile")
	public String postprofile(HttpServletRequest request,@RequestParam("filename")MultipartFile mfile,Principal principal,Model m) {
		
//		System.out.println(mfile.getName());
//		System.out.println(mfile.getContentType());
//		System.out.println(mfile.getOriginalFilename());
//		System.out.println(mfile.getSize());
		
//		String path = "C:\\ikosmo64\\spring\\projectspace\\hdERP\\src\\main\\resources\\static\\img\\"+principal.getName(); //���� ���
//		File Folder = new File(path);
		
		
		// �ش� ���丮�� ������� ���丮�� �����մϴ�.
//		if (!Folder.exists()) {
//			try{
//			    Folder.mkdir(); //���� �����մϴ�.
//			    System.out.println("������ �����Ǿ����ϴ�.");
//			    System.out.println(Folder.getPath());
//		        } 
//		        catch(Exception e){
//			    e.getStackTrace();
//			}        
//	         }else {
//			System.out.println("�̹� ������ �����Ǿ� �ֽ��ϴ�.");
//		}
		
		
		
		
//		
//		try {
//			mfile.transferTo(new File(path+"\\"+mfile.getOriginalFilename()));
//		} catch (IllegalStateException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		HttpSession session = request.getSession();
		String r_path=session.getServletContext().getRealPath("");
		
		System.out.println("rPath : "+r_path);
		String img_path ="static\\img\\";
		System.out.println("imgPath : "+r_path);
		StringBuffer path= new StringBuffer();
		path.append(r_path).append(img_path);
		//�̹��� �̸��� ����
		String oriFn = mfile.getOriginalFilename(); //���ε� �� �̹��� �̸�
		path.append(oriFn);
		System.out.println("FullPath : "+path);//���� �̹����� ����� ���
		//���� ���ε� ����
		File f = new File(path.toString());
		try {
			mfile.transferTo(f); //�������� transferTo�� ȣ���ؼ� �̹����� ������ҿ� ����
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.addAttribute("path", path);
		
		return"redirect:/user.profile";
	}
}
