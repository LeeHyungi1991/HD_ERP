package hd.erp.dto;
/*
BC_NUM           NOT NULL NUMBER(19)         
BC_CONTENT                CLOB               
BC_DATE                   TIMESTAMP(6)       
BC_WRITER                 VARCHAR2(255 CHAR) 
BC_REPLY                  NUMBER(19)         
BOARD_B_NUM               NUMBER(19)         
EMPLOYEE_HD_CODE          NUMBER(19)               
BC_DEPTH                  NUMBER(10)       
 * 
 * */

import java.util.Date;

import org.apache.ibatis.type.Alias;

//@Data ·Òº¹ ¤Ð¤Ð
@Alias(value = "bc")
public class BcommentDTO {

	private Long bc_num,bc_reply,board_b_num,employee_hd_code,bc_depth;
	private String bc_content,bc_writer;
	private Date bc_date;
	public Long getBc_num() {
		return bc_num;
	}
	public void setBc_num(Long bc_num) {
		this.bc_num = bc_num;
	}
	public Long getBc_reply() {
		return bc_reply;
	}
	public void setBc_reply(Long bc_reply) {
		this.bc_reply = bc_reply;
	}
	public Long getBoard_b_num() {
		return board_b_num;
	}
	public void setBoard_b_num(Long board_b_num) {
		this.board_b_num = board_b_num;
	}
	public Long getEmployee_hd_code() {
		return employee_hd_code;
	}
	public void setEmployee_hd_code(Long employee_hd_code) {
		this.employee_hd_code = employee_hd_code;
	}
	public Long getBc_depth() {
		return bc_depth;
	}
	public void setBc_depth(Long bc_depth) {
		this.bc_depth = bc_depth;
	}
	public String getBc_content() {
		return bc_content;
	}
	public void setBc_content(String bc_content) {
		this.bc_content = bc_content;
	}
	public String getBc_writer() {
		return bc_writer;
	}
	public void setBc_writer(String bc_writer) {
		this.bc_writer = bc_writer;
	}
	public Date getBc_date() {
		return bc_date;
	}
	public void setBc_date(Date bc_date) {
		this.bc_date = bc_date;
	}
	
	
}
