package hd.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeManageController {
	@GetMapping(value = "/user.empmanage")
	public String manageindex() {
		return "empManage/Emanage";
	}
	@GetMapping(value = "/user.docwrite")
	public String docwrite() {
		return "empManage/docwrite";
	}
	@GetMapping(value = "/user.document")
	public String document() {
		return "empManage/document";
	}
}
