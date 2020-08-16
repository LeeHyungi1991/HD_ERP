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

import lombok.Data;
@Data
@Alias(value = "bc")
public class BcommentDTO {

	private Long bc_num,bc_reply,board_b_num,employee_hd_code,bc_depth;
	private String bc_content,bc_writer;
	private Date bc_date;
}
