package hd.erp.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import hd.erp.entity.DocAttachmentEntity;
import hd.erp.entity.DocumentEntity;
import hd.erp.entity.EmployeeEntity;
import hd.erp.service.EmployeeManageService;

@Controller
public class EmployeeManageController {
	@Autowired
	EmployeeManageService employeemanageservice;
	
	//사원관리페이지
	@GetMapping(value = "/user.empmanage")
	public String manageindex(Model m) {
		List<EmployeeEntity> emplist = employeemanageservice.getemplist();
		
		m.addAttribute("emplist", emplist);
		
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
	//서류 기안하기 폼전송//file 도 있음
	@PostMapping(value = "/user.docwrite")
	public String docwriteform(DocumentEntity document, Principal principal
			,String docfirstemp_hdcode
			,String docsecondemp_hdcode
			,String docthirdemp_hdcode
			,@RequestParam(name = "file",required = false)MultipartFile[] file) {
		System.out.println(document);
		System.out.println("서류제목"+document.getDoctitle());
		System.out.println("서류내용"+document.getDoccontent());
		System.out.println("서류1결재"+docfirstemp_hdcode);
		System.out.println("서류2결재"+docsecondemp_hdcode);
		System.out.println("서류3결재"+docthirdemp_hdcode);
		System.out.println("기안자:"+principal.getName());
		System.out.println("파일 길이 " + file.length);
		System.out.println("파일 사이즈 " + file[0].getSize());
		
		employeemanageservice.documentinsert(document, docfirstemp_hdcode, docsecondemp_hdcode, docthirdemp_hdcode, principal.getName(),file);
		
		
		return"redirect:/user.docmanage";
	}
	
	//서류 기각
	@PostMapping("/user.docignore")
	public String docignore(DocumentEntity document, String ignorecomment,Principal principal) {
		System.out.println(ignorecomment);
		employeemanageservice.docignore(document.getDocnum(), document, Long.parseLong(principal.getName()), ignorecomment);
		return "redirect:/user.docmanage";
	}
	//서류 승인
	@PostMapping("/user.docok")
	public String docok(DocumentEntity document, String okcomment,Principal principal) {
		System.out.println(okcomment);
		employeemanageservice.docok(document, okcomment);
		return "redirect:/user.docmanage";
		
	}
	
	
	//서류관리 클릭
	@GetMapping(value = "/user.docmanage")
	public String documentmanage(Model m, Principal principal) {
		Map<String, List<DocumentEntity>>docs = employeemanageservice.godocmanage();
		
		List<DocumentEntity> completedoc = docs.get("completedoc");
		List<DocumentEntity> ignoredoc = docs.get("ignoredoc");
		List<DocumentEntity> ingdoc = docs.get("ingdoc");
		
		m.addAttribute("completedoc", completedoc);
		m.addAttribute("ignoredoc", ignoredoc);
		m.addAttribute("ingdoc", ingdoc);
		EmployeeEntity myemp = employeemanageservice.getemp(Long.parseLong(principal.getName()));
		
		m.addAttribute("myemp", myemp);
		
		return "empManage/docmanage";
	}
	//서류보기
	@GetMapping(value = "/user.document")
	public String docuement(String docnum, Model m , Principal principal) {
		System.out.println("docnum>>"+docnum);
		DocumentEntity doc = employeemanageservice.getdoc(Long.parseLong(docnum));
		m.addAttribute("doc", doc);
		String emp1path=null;
		String emp2path=null;
		String emp3path=null;
		if(doc.getDocfirstemp() != null) {
			emp1path = "img/"+doc.getDocfirstemp().getHdcode()+"/signature.png";
			m.addAttribute("emp1path", emp1path);
		}
		if(doc.getDocsecondemp() != null) {
			emp2path = "img/"+doc.getDocsecondemp().getHdcode()+"/signature.png";
			m.addAttribute("emp2path", emp2path);
		}
		if(doc.getDocthirdemp() != null) {
			emp3path = "img/"+doc.getDocthirdemp().getHdcode()+"/signature.png";
			m.addAttribute("emp3path", emp3path);
		}
		EmployeeEntity myemp = employeemanageservice.getemp(Long.parseLong(principal.getName()));
		m.addAttribute("myemp", myemp);
		List<DocAttachmentEntity> att = employeemanageservice.getatt(doc);
		m.addAttribute("att", att);
		m.addAttribute("html", "/user.fileDown?fileName=");
		return "empManage/document";
	}
	//서류삭제
	@PostMapping(value = "/user.docdelete")
	public String deletedoc(String docnum) {
		employeemanageservice.deletedoc(docnum);
		return "redirect:/user.docmanage";
	}
	
	//서류수정
	@GetMapping(value = "/user.docupdate")
	public String docupdate(String docnum,Model m,Principal principal) {
		Map<String, List<EmployeeEntity>> empmap = employeemanageservice.emplist();
		List<EmployeeEntity> adminlist = empmap.get("adminlist");
		List<EmployeeEntity> memberlist = empmap.get("memberlist");
		List<EmployeeEntity> userlist = empmap.get("userlist");
		
		m.addAttribute("adminlist", adminlist);
		m.addAttribute("memberlist", memberlist);
		m.addAttribute("userlist", userlist);
		System.out.println("docnum>>>> up" + docnum);
		DocumentEntity doc =employeemanageservice.getdoc(Long.parseLong(docnum));
		EmployeeEntity myemp = employeemanageservice.getemp(Long.parseLong(principal.getName()));
		m.addAttribute("doc", doc);
		m.addAttribute("myemp",myemp);
		
		
		List<DocAttachmentEntity> att = employeemanageservice.getatt(doc);
		m.addAttribute("att", att);
		
		return "empManage/docupdate";
	}
	
	
	//서류 기안 이미지 처리
	@PostMapping(value = "/user.documentwriteimg",produces = "application/json")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile file){
		return employeemanageservice.summernoteimgupload(file);
	}
	
	//파일다운
	@RequestMapping(value="/user.fileDown")
	public String filedown(@RequestParam("fileName") String fileName,
			HttpSession session,HttpServletRequest request, HttpServletResponse response,Principal principal
			) throws IOException{
		
		employeemanageservice.filedown(request,response,fileName,principal.getName());
		
		return "redirect:/user.docmanage";
	}
	
	
	//서류 첨부파일 처리//ㄴㄴ
	@PostMapping(value = "/user.docattupload")
	@ResponseBody
	public String asdf(@RequestParam("files") MultipartFile[] formData,Principal principal) {
		
		String check = employeemanageservice.attachment(formData,Long.parseLong(principal.getName()));
		
		return check;
	}

	
}
