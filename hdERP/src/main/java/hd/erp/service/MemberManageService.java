package hd.erp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hd.erp.dao.MemberDAO;
import hd.erp.dto.MemberDTO;

@Service
public class MemberManageService {
	
	@Autowired
	MemberDAO memberdao;
	//멤버리스트 가져오기
	public List<MemberDTO> getmemberlist() {
		List<MemberDTO> memlist = memberdao.memberlist();
		return memlist;
	}
	//멤버 가져오기
	public String getmember(int mem_code) {
		JSONObject jsonobject = new JSONObject();
		MemberDTO member = memberdao.member(mem_code);
		jsonobject.put("mem_code", member.getMem_code());
		jsonobject.put("mem_email", member.getMem_email());
		jsonobject.put("mem_pwd", member.getMem_pwd());
		jsonobject.put("mem_name", member.getMem_name());
		jsonobject.put("mem_phn", member.getMem_phn());
		jsonobject.put("mem_in_date", new SimpleDateFormat("yyyy-MM-dd").format(member.getMem_in_date()));
		jsonobject.put("mem_pri_chk", member.getMem_pri_chk());
		jsonobject.put("mem_email_chk", member.getMem_email_chk());
		jsonobject.put("mem_birth", member.getMem_birth());
		jsonobject.put("mem_gender", member.getMem_gender());
		jsonobject.put("mem_loc", member.getMem_loc());
		jsonobject.put("mem_job", member.getMem_job());
		jsonobject.put("mem_reip", member.getMem_reip() );
		
		return jsonobject.toString();
	}
	public void memupdate(String str) {
		JSONObject json = new JSONObject(str);
		MemberDTO member = new MemberDTO();
		member.setMem_birth(json.getString("mem_birth"));
		member.setMem_code(json.getInt("mem_code"));
		member.setMem_name(json.getString("mem_name"));
		member.setMem_email(json.getString("mem_email"));
		member.setMem_email_chk(json.getInt("mem_email_chk"));
		member.setMem_gender(json.getString("mem_gender"));
		try {
			member.setMem_in_date(new SimpleDateFormat("yyyy-MM-dd").parse(json.getString("mem_in_date")));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		member.setMem_job(json.getInt("mem_job"));
		member.setMem_loc(json.getString("mem_loc"));
		member.setMem_name(json.getString("mem_name"));
		member.setMem_phn(json.getString("mem_phn"));
		member.setMem_pri_chk(json.getInt("mem_pri_chk"));
		member.setMem_pwd(json.getString("mem_pwd"));
		member.setMem_reip(json.getString("mem_reip"));
		memberdao.memberupdate(member);
		System.out.println("asdf");
		
	}
	public void memdelete(String mem_code) {
		System.out.println("memdelete 싫행전");
		memberdao.memberdelete(mem_code);
		System.out.println("memdelete 실행함");
	}
	public void meminsert(String str) {
		JSONObject json = new JSONObject(str);
		MemberDTO member = new MemberDTO();
		member.setMem_birth(json.getString("mem_birth"));
		//member.setMem_code(json.getInt("mem_code"));
		member.setMem_name(json.getString("mem_name"));
		member.setMem_email(json.getString("mem_email"));
		member.setMem_email_chk(json.getInt("mem_email_chk"));
		member.setMem_gender(json.getString("mem_gender"));
		try {
			member.setMem_in_date(new SimpleDateFormat("yyyy-MM-dd").parse(json.getString("mem_in_date")));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		member.setMem_job(json.getInt("mem_job"));
		member.setMem_loc(json.getString("mem_loc"));
		member.setMem_name(json.getString("mem_name"));
		member.setMem_phn(json.getString("mem_phn"));
		member.setMem_pri_chk(json.getInt("mem_pri_chk"));
		member.setMem_pwd(json.getString("mem_pwd"));
		member.setMem_reip(json.getString("mem_reip"));
		memberdao.memberinsert(member);
		System.out.println("asdf");
		
	}

}
