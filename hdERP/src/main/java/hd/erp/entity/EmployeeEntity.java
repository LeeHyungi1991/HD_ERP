package hd.erp.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import hd.erp.dto.EmployeeDTO.EmployeeDTOBuilder;

/*
 * HD_CODE    NOT NULL NUMBER        
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
@Entity
@Table(name = "employee")
//@Getter롬복 ㅠ
//@Setter롬복 ㅠ
//@NoArgsConstructor(access = AccessLevel.PROTECTED)롬복 ㅠ
public class EmployeeEntity {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "hd_code_sequence")
	@SequenceGenerator(name = "hd_code_sequence",allocationSize = 1,sequenceName = "EMPLOYEE_SEQ")
	@Column(name = "hd_code")
	private Long hdcode;
	
	//직급
	@Column(name = "hd_level")
	@NotNull
	private String hdlevel;
	
	//가입일
	@Column(name = "hd_in_date")
	@NotNull
	private String hdindate;
	
	//이름
	@Column(name = "hd_name")
	@NotNull
	private String hdname;
	
	//생일
	@Column(name = "hd_birth")
	@NotNull
	private String hdbirth;
	
	//부서명
	@Column(name = "hd_dname")
	private String hddname;
	
	//성별
	@Column(name = "hd_gender")
	private String hdgender;
	
	//전화번호
	@Column(name = "hd_phn")
	@NotNull	
	private String hdphn;
	
	//이메일
	@Column(name = "hd_email")
	private String hdemail;
	
	//지역
	@Column(name = "hd_loc")
	private String hdloc;
	
	//비번
	@Column(name = "hd_pw")
	@NotNull
	private String hdpw;
	
	//어드민비번
	@Column(name = "hd_admin_pw")
	@NotNull
	private String hdadminpw;

	
	public static EmployeeEntityBuilder builder() {
		return new EmployeeEntityBuilder();
	}
	
	
	
	
	public Long getHdcode() {
		return hdcode;
	}


	public void setHdcode(Long hdcode) {
		this.hdcode = hdcode;
	}


	public String getHdlevel() {
		return hdlevel;
	}


	public void setHdlevel(String hdlevel) {
		this.hdlevel = hdlevel;
	}


	public String getHdindate() {
		return hdindate;
	}


	public void setHdindate(String hdindate) {
		this.hdindate = hdindate;
	}


	public String getHdname() {
		return hdname;
	}


	public void setHdname(String hdname) {
		this.hdname = hdname;
	}


	public String getHdbirth() {
		return hdbirth;
	}


	public void setHdbirth(String hdbirth) {
		this.hdbirth = hdbirth;
	}


	public String getHddname() {
		return hddname;
	}


	public void setHddname(String hddname) {
		this.hddname = hddname;
	}


	public String getHdgender() {
		return hdgender;
	}


	public void setHdgender(String hdgender) {
		this.hdgender = hdgender;
	}


	public String getHdphn() {
		return hdphn;
	}


	public void setHdphn(String hdphn) {
		this.hdphn = hdphn;
	}


	public String getHdemail() {
		return hdemail;
	}


	public void setHdemail(String hdemail) {
		this.hdemail = hdemail;
	}


	public String getHdloc() {
		return hdloc;
	}


	public void setHdloc(String hdloc) {
		this.hdloc = hdloc;
	}


	public String getHdpw() {
		return hdpw;
	}


	public void setHdpw(String hdpw) {
		this.hdpw = hdpw;
	}


	public String getHdadminpw() {
		return hdadminpw;
	}


	public void setHdadminpw(String hdadminpw) {
		this.hdadminpw = hdadminpw;
	}
	
	
	


	//@Builder 롬복 ㅠ
	public EmployeeEntity(Long hdcode, String hdlevel, String hdindate, String hdname, String hdbirth, String hddname,
			String hdgender, String hdphn, String hdemail, String hdloc, String hdpw,String hdadminpw) {
		super();
		this.hdcode = hdcode;
		this.hdlevel = hdlevel;
		this.hdindate = hdindate;
		this.hdname = hdname;
		this.hdbirth = hdbirth;
		this.hddname = hddname;
		this.hdgender = hdgender;
		this.hdphn = hdphn;
		this.hdemail = hdemail;
		this.hdloc = hdloc;
		this.hdpw = hdpw;
		this.hdadminpw = hdadminpw;
	}


	protected EmployeeEntity() {} //기본생성자
	
	
	public static class EmployeeEntityBuilder{
		private Long hdcode;
		private String hdlevel;
		private String hdindate ;
		private String hdname ;
		private String hdbirth ;
		private String hddname ;
		private String hdgender ;
		private String hdphn ;
		private String hdemail ;
		private String hdloc ;
		private String hdpw ;
		private String hdadminpw ;
		
		
		public EmployeeEntity build() {
			EmployeeEntity employeeentity = new EmployeeEntity();
			
			employeeentity.setHdcode(this.hdcode);
			employeeentity.setHdlevel(this.hdlevel);
			employeeentity.setHdindate(this.hdindate);
			employeeentity.setHdname(this.hdname);
			employeeentity.setHdbirth(this.hdbirth);
			employeeentity.setHddname(this.hddname);
			employeeentity.setHdgender(this.hdgender);
			employeeentity.setHdphn(this.hdphn);
			employeeentity.setHdemail(this.hdemail);
			employeeentity.setHdloc(this.hdloc);
			employeeentity.setHdpw(this.hdpw);
			employeeentity.setHdadminpw(this.hdadminpw);
			
			return employeeentity;
		}
		public EmployeeEntityBuilder hdcode(Long hdcode) {
			this.hdcode = hdcode;
			return this;
		}
		
		public EmployeeEntityBuilder hdlevel(String hdlevel) {
			this.hdlevel =hdlevel;
			return this;
		}
		public EmployeeEntityBuilder hdname(String hdname) {
			this.hdname =hdname;
			return this;
		}
		public EmployeeEntityBuilder hddname(String hddname) {
			this.hddname =hddname;
			return this;
		}
		
		public EmployeeEntityBuilder hdgender(String hdgender) {
			this.hdgender =hdgender;
			return this;
		}
		
		public EmployeeEntityBuilder hdphn(String hdphn) {
			this.hdphn =hdphn;
			return this;
		}
		
		public EmployeeEntityBuilder hdemail(String hdemail) {
			this.hdemail =hdemail;
			return this;
		}
		
		public EmployeeEntityBuilder hdpw(String hdpw) {
			this.hdpw =hdpw;
			return this;
		}
		
		public EmployeeEntityBuilder hdindate(String hdindate) {
			this.hdindate =hdindate;
			return this;
		}
		
		public EmployeeEntityBuilder hdbirth(String hdbirth) {
			this.hdbirth =hdbirth;
			return this;
		}
		
		public EmployeeEntityBuilder hdadminpw(String hdadminpw) {
			this.hdadminpw =hdadminpw;
			return this;
		}
		
		public EmployeeEntityBuilder hdloc(String hdloc) {
			this.hdloc =hdloc;
			return this;
		}
		
		
		
	}
	
	
	
	

}
