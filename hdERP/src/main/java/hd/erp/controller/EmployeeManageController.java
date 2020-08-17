package hd.erp.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import hd.erp.entity.DocumentEntity;
import hd.erp.entity.EmployeeEntity;
import hd.erp.service.EmployeeManageService;

@Controller
public class EmployeeManageController {
	@Autowired
	EmployeeManageService employeemanageservice;
	
	//사원관리페이지
	@GetMapping(value = "/user.empmanage")
	public String manageindex() {
		return "empManage/Emanage";
	}
	
	
	
	
	
	
	
	
	
	//서류 기안하기 클릭
	@GetMapping(value = "/user.docwrite")
	public String docwrite(Model m,Principal principal) {
		Map<String, List<EmployeeEntity>> empmap = employeemanageservice.emplist();
		List<EmployeeEntity> adminlist = empmap.get("adminlist");
		List<EmployeeEntity> memberlist = empmap.get("memberlist");
		List<EmployeeEntity> userlist = empmap.get("userlist");
		
		m.addAttribute("adminlist", adminlist);
		m.addAttribute("memberlist", memberlist);
		m.addAttribute("userlist", userlist);
		
		EmployeeEntity myemp = employeemanageservice.getmylevel(Long.parseLong(principal.getName()));
		
		m.addAttribute("myemp", myemp);
		
		
		
		return "empManage/docwrite";
	}
	//서류 기안하기 폼전송
	@PostMapping(value = "/user.docwrite")
	public String docwriteform(DocumentEntity document, Principal principal
			,String docfirstemp_hdcode
			,String docsecondemp_hdcode
			,String docthirdemp_hdcode) {
		System.out.println(document);
		System.out.println("서류제목"+document.getDoctitle());
		System.out.println("서류내용"+document.getDoccontent());
		System.out.println("서류1결재"+docfirstemp_hdcode);
		System.out.println("서류2결재"+docsecondemp_hdcode);
		System.out.println("서류3결재"+docthirdemp_hdcode);
		System.out.println("기안자:"+principal.getName());
		employeemanageservice.documentinsert(document, docfirstemp_hdcode, docsecondemp_hdcode, docthirdemp_hdcode, principal.getName());
		
		
		return"redirect:/user.docmanage";
	}
	
	
	
	
	//서류관리 클릭
	@GetMapping(value = "/user.docmanage")
	public String documentmanage(Model m, Principal principal) {
		Map<String, List<DocumentEntity>>docs = employeemanageservice.godocmanage();
		
		List<DocumentEntity> completedoc = docs.get("completedoc");
		List<DocumentEntity> ignoredoc = docs.get("ignoredoc");
		List<DocumentEntity> ingdoc = docs.get("ingdoc");
		
//		m.addAttribute("completedoc", completedoc);
//		m.addAttribute("ignoredoc", ignoredoc);
//		m.addAttribute("ingdoc", ingdoc);
		
		
		return "empManage/docmanage";
	}
	//서류보기
	@GetMapping(value = "/user.document")
	public String docuement() {
		return "empManage/document";
	}
	//서류 기안 이미지 처리
	@PostMapping(value = "/user.documentwriteimg",produces = "application/json")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile file){
		return employeemanageservice.summernoteimgupload(file);
	}
}
