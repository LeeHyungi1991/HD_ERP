package hd.erp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping(value = "/user.memdetail")
	@ResponseBody
	public String memdetail(Model m,String memcode) {
		System.out.println("memcode = "+memcode);
		
		
		String member = membermanageservice.getmember(Integer.parseInt(memcode));
		
		System.out.println(member);
		
		return member;
	}
	
	@RequestMapping(value = "/user.memupate")
	@ResponseBody
	public String memupdate(String str) {
		System.out.println("str >>>>"+str);
		System.out.println(str.getClass());
		
		membermanageservice.memupdate(str);
		return "ok";
		
	}
	
	//이거 왜안됨??? 오라클 멈추는데?
	@RequestMapping(value = "/user.memdelete")
	@ResponseBody
	public String memdelete(String mem_code) {
		System.out.println(mem_code);
		membermanageservice.memdelete(mem_code);
		
		return "ok";
	}
	//이거 왜안됨??? 오라클 멈추는데?
	@RequestMapping(value = "/user.meminsert")
	@ResponseBody
	public String meminsert(String str) {
		System.out.println("str >>>>"+str);
		System.out.println(str.getClass());
		
		membermanageservice.meminsert(str);
		return "ok";
	}

}
