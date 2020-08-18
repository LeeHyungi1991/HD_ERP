package hd.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hd.erp.dto.MemberDTO;
import hd.erp.service.MemberManageService;

@Controller
public class MemberManageController {
	
	@Autowired
	MemberManageService membermanageservice;
	
	@GetMapping(value = "/user.memmanage")
	public String manageindex(Model m) {
		
		List<MemberDTO> memlist =membermanageservice.getmemberlist();
		
		m.addAttribute("memlist", memlist);
		return"memManage/Mmanage";
	}
	

}
