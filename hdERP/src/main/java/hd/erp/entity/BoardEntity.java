package hd.erp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "board")
@Data
public class BoardEntity {
	
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "board_seq")
	@SequenceGenerator(allocationSize = 1,name = "board_seq",sequenceName = "board_seq")
	@Column(name = "b_num")
	private Long bnum;
	
	@Column(name = "b_hit")
	private  Long bhit;
	
	@Column(name = "b_date")
	@Temporal(TemporalType.DATE)
	private Date bdate;
	
	@Column(name = "b_title")
	private String btitle;
	
	@Column(name = "b_content")
	@Lob
	private String bcontent;
	
	@Column(name = "b_like")
	private Long blike;
	
	@ManyToOne@JoinColumn(name = "employee_hd_code")
	private EmployeeEntity employee;

}
