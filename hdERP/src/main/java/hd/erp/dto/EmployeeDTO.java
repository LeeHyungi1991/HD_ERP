package hd.erp.dto;


import groovy.transform.ToString;
import hd.erp.entity.EmployeeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmployeeDTO {
	
	private Long hd_code;
	private String hd_level,hd_name,hd_dname,hd_gender,hd_phn
			,hd_email,hd_loc,hd_pw;
	private String hd_in_date;
	private String hd_birth;
	
	public EmployeeEntity toEntity() {
		return EmployeeEntity.builder()
				.hdcode(hd_code).hd_level(hd_level).hd_name(hd_name)
				.hd_dname(hd_dname).hd_gender(hd_gender).hd_phn(hd_phn)
				.hd_email(hd_email).hd_loc(hd_loc).hd_pw(hd_pw)
				.hd_in_date(hd_in_date).hd_birth(hd_birth).build();
	}
	
	
	@Builder
	public EmployeeDTO(Long hd_code, String hd_level, String hd_name, String hd_dname, String hd_gender, String hd_phn,
			String hd_email, String hd_loc, String hd_pw, String hd_in_date, String hd_birth) {
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
	}
	
	
	
	
	
	
}
