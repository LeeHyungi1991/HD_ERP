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





@Table(name = "document")
@Entity
//@Data �Һ� ��
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

	public Long getDocnum() {
		return docnum;
	}

	public void setDocnum(Long docnum) {
		this.docnum = docnum;
	}

	public EmployeeEntity getDocdrafter() {
		return docdrafter;
	}

	public void setDocdrafter(EmployeeEntity docdrafter) {
		this.docdrafter = docdrafter;
	}

	public Date getDocdate() {
		return docdate;
	}

	public void setDocdate(Date docdate) {
		this.docdate = docdate;
	}

	public int getDocstatus() {
		return docstatus;
	}

	public void setDocstatus(int docstatus) {
		this.docstatus = docstatus;
	}

	public String getDoctitle() {
		return doctitle;
	}

	public void setDoctitle(String doctitle) {
		this.doctitle = doctitle;
	}

	public String getDoccontent() {
		return doccontent;
	}

	public void setDoccontent(String doccontent) {
		this.doccontent = doccontent;
	}

	public String getDoc1okcomment() {
		return doc1okcomment;
	}

	public void setDoc1okcomment(String doc1okcomment) {
		this.doc1okcomment = doc1okcomment;
	}

	public String getDoc2okcomment() {
		return doc2okcomment;
	}

	public void setDoc2okcomment(String doc2okcomment) {
		this.doc2okcomment = doc2okcomment;
	}

	public String getDoc3okcomment() {
		return doc3okcomment;
	}

	public void setDoc3okcomment(String doc3okcomment) {
		this.doc3okcomment = doc3okcomment;
	}

	public String getDoc1ignorecomment() {
		return doc1ignorecomment;
	}

	public void setDoc1ignorecomment(String doc1ignorecomment) {
		this.doc1ignorecomment = doc1ignorecomment;
	}

	public String getDoc2ignorecomment() {
		return doc2ignorecomment;
	}

	public void setDoc2ignorecomment(String doc2ignorecomment) {
		this.doc2ignorecomment = doc2ignorecomment;
	}

	public String getDoc3ignorecomment() {
		return doc3ignorecomment;
	}

	public void setDoc3ignorecomment(String doc3ignorecomment) {
		this.doc3ignorecomment = doc3ignorecomment;
	}

	public EmployeeEntity getDocfirstemp() {
		return docfirstemp;
	}

	public void setDocfirstemp(EmployeeEntity docfirstemp) {
		this.docfirstemp = docfirstemp;
	}

	public EmployeeEntity getDocsecondemp() {
		return docsecondemp;
	}

	public void setDocsecondemp(EmployeeEntity docsecondemp) {
		this.docsecondemp = docsecondemp;
	}

	public EmployeeEntity getDocthirdemp() {
		return docthirdemp;
	}

	public void setDocthirdemp(EmployeeEntity docthirdemp) {
		this.docthirdemp = docthirdemp;
	}

	public EmployeeEntity getDocignoreemp() {
		return docignoreemp;
	}

	public void setDocignoreemp(EmployeeEntity docignoreemp) {
		this.docignoreemp = docignoreemp;
	}

	public String getDocignorestatus() {
		return docignorestatus;
	}

	public void setDocignorestatus(String docignorestatus) {
		this.docignorestatus = docignorestatus;
	}
	
	
	
	
	
	
	
	
	
}
