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
	
	//�������������
	@GetMapping(value = "/user.empmanage")
	public String manageindex() {
		return "empManage/Emanage";
	}
	
	
	
	
	
	
	
	
	
	//���� ����ϱ� Ŭ��
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
	//���� ����ϱ� ������
	@PostMapping(value = "/user.docwrite")
	public String docwriteform(DocumentEntity document, Principal principal
			,String docfirstemp_hdcode
			,String docsecondemp_hdcode
			,String docthirdemp_hdcode) {
		System.out.println(document);
		System.out.println("��������"+document.getDoctitle());
		System.out.println("��������"+document.getDoccontent());
		System.out.println("����1����"+docfirstemp_hdcode);
		System.out.println("����2����"+docsecondemp_hdcode);
		System.out.println("����3����"+docthirdemp_hdcode);
		System.out.println("�����:"+principal.getName());
		employeemanageservice.documentinsert(document, docfirstemp_hdcode, docsecondemp_hdcode, docthirdemp_hdcode, principal.getName());
		
		
		return"redirect:/user.docmanage";
	}
	
	
	
	
	//�������� Ŭ��
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
	//��������
	@GetMapping(value = "/user.document")
	public String docuement() {
		return "empManage/document";
	}
	//���� ��� �̹��� ó��
	@PostMapping(value = "/user.documentwriteimg",produces = "application/json")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile file){
		return employeemanageservice.summernoteimgupload(file);
	}
}
