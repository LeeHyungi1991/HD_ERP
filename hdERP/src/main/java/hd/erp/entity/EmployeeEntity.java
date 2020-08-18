package hd.erp.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.NonFinal;
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
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

	
	@Builder
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
	
	
	
	

}
