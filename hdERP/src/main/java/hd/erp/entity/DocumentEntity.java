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
	//���� ��ȣ
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "doc_seq")
	@SequenceGenerator(allocationSize = 1,name = "doc_seq",sequenceName = "doc_seq")
	@Column(name = "doc_num")
	private Long docnum;
	
	//���� �����
	@JoinColumn(name = "doc_drafter")
	@ManyToOne(targetEntity = EmployeeEntity.class)
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private EmployeeEntity docdrafter;
	
	//���� �����
	@Column(name = "doc_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date docdate;
	
	//���� �����Ȳ (-1,0,1,2,3)
	@Column(name = "doc_status")
	private int docstatus;
	
	//���� ����
	@Column(name = "doc_title")
	private String doctitle;
	
	//���� ����
	@Column(name = "doc_content")
	@Lob
	private String doccontent;
	
	
	//���� 1�������ڸ�Ʈ
	@Column(name = "doc_1_okcomment")
	private String doc1okcomment;
	
	//���� 2�������ڸ�Ʈ
	@Column(name = "doc_2_okcomment")
	private String doc2okcomment;
	
	//���� 3�������ڸ�Ʈ
	@Column(name = "doc_3_okcomment")
	private String doc3okcomment;
	
	//���� 1�� �Ⱒ �ڸ�Ʈ
	@Column(name = "doc_1_ignorecomment")
	private String doc1ignorecomment;
	
	//���� 2�� �Ⱒ �ڸ�Ʈ
	@Column(name = "doc_2_ignorecomment")
	private String doc2ignorecomment;
	
	//���� 3�� �Ⱒ �ڸ�Ʈ
	@Column(name = "doc_3_ignorecomment")
	private String doc3ignorecomment;
	
	//1�� ������
	@JoinColumn(name = "doc_first_emp")
	@ManyToOne(targetEntity = EmployeeEntity.class)
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private EmployeeEntity docfirstemp;
	
	//2�� ������
	@JoinColumn(name = "doc_second_emp")
	@ManyToOne(targetEntity = EmployeeEntity.class)
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private EmployeeEntity docsecondemp;
	
	//3�� ������
	@JoinColumn(name = "doc_third_emp")
	@ManyToOne(targetEntity = EmployeeEntity.class)
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private EmployeeEntity docthirdemp;
	
	//�Ⱒ�� ���
	@JoinColumn(name = "doc_ignore_emp")
	@ManyToOne(targetEntity = EmployeeEntity.class)
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private EmployeeEntity docignoreemp;
		
	//�Ⱒ�� ���� ����
	@Column(name = "doc_ignore_status")
	private String docignorestatus;
	
	
}
