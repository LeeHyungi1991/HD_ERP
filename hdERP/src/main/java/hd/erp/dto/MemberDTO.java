package hd.erp.dto;
/*
 * 
MEM_CODE      NOT NULL NUMBER        
MEM_EMAIL     NOT NULL VARCHAR2(100) 
MEM_PWD       NOT NULL VARCHAR2(50)  
MEM_NAME      NOT NULL VARCHAR2(30)  
MEM_PHN       NOT NULL VARCHAR2(30)  
MEM_IN_DATE   NOT NULL DATE          
MEM_PRI_CHK   NOT NULL NUMBER        
MEM_EMAIL_CHK NOT NULL NUMBER        
MEM_BIRTH     NOT NULL CHAR(6)       
MEM_GENDER    NOT NULL CHAR(1)       
MEM_LOC       NOT NULL VARCHAR2(100) 
MEM_JOB                NUMBER        
MEM_REIP               VARCHAR2(20)  
 * */

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;
@Data
@Alias(value = "mem")
public class MemberDTO {

	private int mem_code,mem_pri_chk,mem_email_chk,mem_job;
	private String mem_email,mem_pwd,mem_name,mem_phn,mem_birth,mem_gender,mem_log,mem_reip;
	private Date mem_in_date;
	
}
