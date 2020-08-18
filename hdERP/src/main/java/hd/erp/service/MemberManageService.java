package hd.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hd.erp.dao.MemberDAO;
import hd.erp.dto.MemberDTO;

@Service
public class MemberManageService {
	
	@Autowired
	MemberDAO memberdao;
	
	public List<MemberDTO> getmemberlist() {
		List<MemberDTO> memlist = memberdao.memberlist();
		return memlist;
	}

}
