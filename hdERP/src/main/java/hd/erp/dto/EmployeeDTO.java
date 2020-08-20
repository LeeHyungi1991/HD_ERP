package hd.erp.dto;


import org.apache.ibatis.type.Alias;

import groovy.transform.ToString;
import hd.erp.entity.EmployeeEntity;
import hd.erp.entity.EmployeeEntity.EmployeeEntityBuilder;


/*
HD_CODE    NOT NULL NUMBER        
HD_LEVEL   NOT NULL VARCHAR2(30)  
HD_IN_DATE NOT NULL DATE          
HD_NAME    NOT NULL VARCHAR2(30)  
HD_BIRTH   NOT NULL DATE          
HD_DNAME            VARCHAR2(30)  
HD_GENDER           VARCHAR2(30)  
HD_PHN     NOT NULL VARCHAR2(30)  
HD_EMAIL            VARCHAR2(30)  
HD_LOC              VARCHAR2(100) 
HD_PW      NOT NULL VARCHAR2(255) 
 * */
//@Getter 롬복 ㅠㅠ
//@Setter 롬복 ㅠㅠ 
//@NoArgsConstructor 롬복 ㅠㅠ
@ToString
@Alias(value = "emp")
public class EmployeeDTO {
	
	private Long hd_code;
	private String hd_level,hd_name,hd_dname,hd_gender,hd_phn
			,hd_email,hd_loc,hd_pw;
	private String hd_in_date;
	private String hd_birth;
	private String hd_admin_pw;
	
	
	
	
	
	
	public Long getHd_code() {
		return hd_code;
	}


	public void setHd_code(Long hd_code) {
		this.hd_code = hd_code;
	}


	public String getHd_level() {
		return hd_level;
	}


	public void setHd_level(String hd_level) {
		this.hd_level = hd_level;
	}


	public String getHd_name() {
		return hd_name;
	}


	public void setHd_name(String hd_name) {
		this.hd_name = hd_name;
	}


	public String getHd_dname() {
		return hd_dname;
	}


	public void setHd_dname(String hd_dname) {
		this.hd_dname = hd_dname;
	}


	public String getHd_gender() {
		return hd_gender;
	}


	public void setHd_gender(String hd_gender) {
		this.hd_gender = hd_gender;
	}


	public String getHd_phn() {
		return hd_phn;
	}


	public void setHd_phn(String hd_phn) {
		this.hd_phn = hd_phn;
	}


	public String getHd_email() {
		return hd_email;
	}


	public void setHd_email(String hd_email) {
		this.hd_email = hd_email;
	}


	public String getHd_loc() {
		return hd_loc;
	}


	public void setHd_loc(String hd_loc) {
		this.hd_loc = hd_loc;
	}


	public String getHd_pw() {
		return hd_pw;
	}


	public void setHd_pw(String hd_pw) {
		this.hd_pw = hd_pw;
	}


	public String getHd_in_date() {
		return hd_in_date;
	}


	public void setHd_in_date(String hd_in_date) {
		this.hd_in_date = hd_in_date;
	}


	public String getHd_birth() {
		return hd_birth;
	}


	public void setHd_birth(String hd_birth) {
		this.hd_birth = hd_birth;
	}


	public String getHd_admin_pw() {
		return hd_admin_pw;
	}


	public void setHd_admin_pw(String hd_admin_pw) {
		this.hd_admin_pw = hd_admin_pw;
	}


	public EmployeeEntity toEntity() {
		return EmployeeEntity.builder()
				.hdcode(hd_code).hdlevel(hd_level).hdname(hd_name)
				.hddname(hd_dname).hdgender(hd_gender).hdphn(hd_phn)
				.hdemail(hd_email).hdloc(hd_loc).hdpw(hd_pw)
				.hdindate(hd_in_date).hdbirth(hd_birth).hdadminpw(hd_admin_pw).build();
	}
	
	
	//@Builder 롬복 ㅠㅠ
	public EmployeeDTO(Long hd_code, String hd_level, String hd_name, String hd_dname, String hd_gender, String hd_phn,
			String hd_email, String hd_loc, String hd_pw, String hd_in_date, String hd_birth,String hd_admin_pw) {
		super();
		this.hd_code = hd_code;
		this.hd_level = hd_level;
		this.hd_name = hd_name;
		this.hd_dname = hd_dname;
		this.hd_gender = hd_gender;
		this.hd_phn = hd_phn;
		this.hd_email = hd_email;
		this.hd_loc = hd_loc;
		this.hd_pw = hd_pw;
		this.hd_in_date = hd_in_date;
		this.hd_birth = hd_birth;
		this.hd_admin_pw= hd_admin_pw;
	}


	public EmployeeDTO() {	} //기본 생성자
	
	public class EmployeeDTOBuilder{ //빌더
		private Long hd_code;
		private String hd_level,hd_name,hd_dname,hd_gender,hd_phn
				,hd_email,hd_loc,hd_pw;
		private String hd_in_date;
		private String hd_birth;
		private String hd_admin_pw;
		
		public EmployeeDTO build() {
			EmployeeDTO employeedto = new EmployeeDTO();
			employeedto.setHd_code(this.hd_code);
			employeedto.setHd_level(this.hd_level);
			employeedto.setHd_name(this.hd_name);
			employeedto.setHd_dname(this.hd_dname);
			employeedto.setHd_gender(this.hd_gender);
			employeedto.setHd_phn(this.hd_phn);
			employeedto.setHd_email(this.hd_email);
			employeedto.setHd_loc(this.hd_loc);
			employeedto.setHd_pw(this.hd_pw);
			employeedto.setHd_in_date(this.hd_in_date);
			employeedto.setHd_birth(this.hd_birth);
			employeedto.setHd_admin_pw(this.hd_admin_pw);
			return employeedto;
		}
		
		public EmployeeDTOBuilder hd_code(Long hd_code) {
			this.hd_code = hd_code;
			return this;
		}
		
		public EmployeeDTOBuilder hd_level(String hd_level) {
			this.hd_level =hd_level;
			return this;
		}
		public EmployeeDTOBuilder hd_name(String hd_name) {
			this.hd_name =hd_name;
			return this;
		}
		public EmployeeDTOBuilder hd_dname(String hd_dname) {
			this.hd_dname =hd_dname;
			return this;
		}
		
		public EmployeeDTOBuilder hd_gender(String hd_gender) {
			this.hd_gender =hd_gender;
			return this;
		}
		
		public EmployeeDTOBuilder hd_phn(String hd_phn) {
			this.hd_phn =hd_phn;
			return this;
		}
		
		public EmployeeDTOBuilder hd_email(String hd_email) {
			this.hd_email =hd_email;
			return this;
		}
		
		public EmployeeDTOBuilder hd_pw(String hd_pw) {
			this.hd_pw =hd_pw;
			return this;
		}
		
		public EmployeeDTOBuilder hd_in_date(String hd_in_date) {
			this.hd_in_date =hd_in_date;
			return this;
		}
		
		public EmployeeDTOBuilder hd_birth(String hd_birth) {
			this.hd_birth =hd_birth;
			return this;
		}
		
		public EmployeeDTOBuilder hd_admin_pw(String hd_admin_pw) {
			this.hd_admin_pw =hd_admin_pw;
			return this;
		}
		
		public EmployeeDTOBuilder hd_loc(String hd_loc) {
			this.hd_loc =hd_loc;
			return this;
		}
		
	}
	
	
	
	
	
	
}
