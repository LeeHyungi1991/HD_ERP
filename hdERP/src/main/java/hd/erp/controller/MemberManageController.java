package hd.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberManageController {
	
	@GetMapping(value = "/user.memmanage")
	public String manageindex() {
		return"memManage/Mmanage";
	}

}
