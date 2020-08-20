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


//@Data
@Alias(value = "mem")
public class MemberDTO {

	private int mem_code,mem_pri_chk,mem_email_chk,mem_job;
	private String mem_email,mem_pwd,mem_name,mem_phn,mem_birth,mem_gender,mem_loc,mem_reip;
	private Date mem_in_date;
	public int getMem_code() {
		return mem_code;
	}
	public void setMem_code(int mem_code) {
		this.mem_code = mem_code;
	}
	public int getMem_pri_chk() {
		return mem_pri_chk;
	}
	public void setMem_pri_chk(int mem_pri_chk) {
		this.mem_pri_chk = mem_pri_chk;
	}
	public int getMem_email_chk() {
		return mem_email_chk;
	}
	public void setMem_email_chk(int mem_email_chk) {
		this.mem_email_chk = mem_email_chk;
	}
	public int getMem_job() {
		return mem_job;
	}
	public void setMem_job(int mem_job) {
		this.mem_job = mem_job;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_pwd() {
		return mem_pwd;
	}
	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_phn() {
		return mem_phn;
	}
	public void setMem_phn(String mem_phn) {
		this.mem_phn = mem_phn;
	}
	public String getMem_birth() {
		return mem_birth;
	}
	public void setMem_birth(String mem_birth) {
		this.mem_birth = mem_birth;
	}
	public String getMem_gender() {
		return mem_gender;
	}
	public void setMem_gender(String mem_gender) {
		this.mem_gender = mem_gender;
	}
	public String getMem_loc() {
		return mem_loc;
	}
	public void setMem_loc(String mem_loc) {
		this.mem_loc = mem_loc;
	}
	public String getMem_reip() {
		return mem_reip;
	}
	public void setMem_reip(String mem_reip) {
		this.mem_reip = mem_reip;
	}
	public Date getMem_in_date() {
		return mem_in_date;
	}
	public void setMem_in_date(Date mem_in_date) {
		this.mem_in_date = mem_in_date;
	}
	
	
	
	
	
}
