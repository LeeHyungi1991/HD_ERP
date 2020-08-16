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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeEntity {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "hd_code_sequence")
	@SequenceGenerator(name = "hd_code_sequence",allocationSize = 1,sequenceName = "EMPLOYEE_SEQ")
	@Column(name = "hd_code")
	private Long hdcode;
	
	@Column(name = "hd_level")
	@NotNull
	private String hd_level;
	
	@Column(name = "hd_in_date")
	@NotNull
	private String hd_in_date;
	
	@Column(name = "hd_name")
	@NotNull
	private String hdname;
	
	@Column(name = "hd_birth")
	@NotNull
	private String hd_birth;
	
	@Column(name = "hd_dname")
	private String hd_dname;
	
	@Column(name = "hd_gender")
	private String hd_gender;
	
	@Column(name = "hd_phn")
	@NotNull	
	private String hd_phn;
	
	@Column(name = "hd_email")
	private String hd_email;
	
	@Column(name = "hd_loc")
	private String hd_loc;
	
	@Column(name = "hd_pw")
	@NotNull
	private String hd_pw;
	
	@Column(name = "hd_admin_pw")
	@NotNull
	private String hd_admin_pw;

	
	@Builder
	public EmployeeEntity(Long hdcode, String hd_level, String hd_in_date, String hd_name, String hd_birth, String hd_dname,
			String hd_gender, String hd_phn, String hd_email, String hd_loc, String hd_pw,String hd_admin_pw) {
		super();
		this.hdcode = hdcode;
		this.hd_level = hd_level;
		this.hd_in_date = hd_in_date;
		this.hdname = hd_name;
		this.hd_birth = hd_birth;
		this.hd_dname = hd_dname;
		this.hd_gender = hd_gender;
		this.hd_phn = hd_phn;
		this.hd_email = hd_email;
		this.hd_loc = hd_loc;
		this.hd_pw = hd_pw;
		this.hd_admin_pw = hd_admin_pw;
	}
	
	
	
	

}
