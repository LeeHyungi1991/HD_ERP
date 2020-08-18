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

@Table(name = "document")
@Entity
@Data
public class DocumentEntity {
	//서류 번호
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "doc_seq")
	@SequenceGenerator(allocationSize = 1,name = "doc_seq",sequenceName = "doc_seq")
	@Column(name = "doc_num")
	private Long docnum;
	
	//서류 기안자
	@JoinColumn(name = "doc_drafter")
	@ManyToOne(targetEntity = EmployeeEntity.class)
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private EmployeeEntity docdrafter;
	
	//서류 기안일
	@Column(name = "doc_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date docdate;
	
	//서류 진행상황 (-1,0,1,2,3)
	@Column(name = "doc_status")
	private int docstatus;
	
	//서류 제목
	@Column(name = "doc_title")
	private String doctitle;
	
	//서류 내용
	@Column(name = "doc_content")
	@Lob
	private String doccontent;
	
	
	//서류 1차결재코멘트
	@Column(name = "doc_1_okcomment")
	private String doc1okcomment;
	
	//서류 2차결재코멘트
	@Column(name = "doc_2_okcomment")
	private String doc2okcomment;
	
	//서류 3차결재코멘트
	@Column(name = "doc_3_okcomment")
	private String doc3okcomment;
	
	//서류 1차 기각 코멘트
	@Column(name = "doc_1_ignorecomment")
	private String doc1ignorecomment;
	
	//서류 2차 기각 코멘트
	@Column(name = "doc_2_ignorecomment")
	private String doc2ignorecomment;
	
	//서류 3차 기각 코멘트
	@Column(name = "doc_3_ignorecomment")
	private String doc3ignorecomment;
	
	//1차 결재자
	@JoinColumn(name = "doc_first_emp")
	@ManyToOne(targetEntity = EmployeeEntity.class)
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private EmployeeEntity docfirstemp;
	
	//2차 결재자
	@JoinColumn(name = "doc_second_emp")
	@ManyToOne(targetEntity = EmployeeEntity.class)
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private EmployeeEntity docsecondemp;
	
	//3차 결재자
	@JoinColumn(name = "doc_third_emp")
	@ManyToOne(targetEntity = EmployeeEntity.class)
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private EmployeeEntity docthirdemp;
	
	//기각한 사람
	@JoinColumn(name = "doc_ignore_emp")
	@ManyToOne(targetEntity = EmployeeEntity.class)
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private EmployeeEntity docignoreemp;
		
	//기각시 서류 상태
	@Column(name = "doc_ignore_status")
	private String docignorestatus;
	
	
}
