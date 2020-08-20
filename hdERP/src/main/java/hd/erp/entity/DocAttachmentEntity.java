package hd.erp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



@Table(name = "docattachment")
//@Data ทาบน คะ
@Entity
public class DocAttachmentEntity {
	
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "att_seq")
	@SequenceGenerator(allocationSize = 1,name = "att_seq",sequenceName = "att_seq")
	@Column(name = "att_num")
	private Long attnum;
	
	@Column(name = "att_name")
	private String attname;
	
	@Column(name = "att_savedname")
	private String attsavedname;

	@ManyToOne
	@JoinColumn(name="document_doc_num")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private DocumentEntity document;

	public Long getAttnum() {
		return attnum;
	}

	public void setAttnum(Long attnum) {
		this.attnum = attnum;
	}

	public String getAttname() {
		return attname;
	}

	public void setAttname(String attname) {
		this.attname = attname;
	}

	public String getAttsavedname() {
		return attsavedname;
	}

	public void setAttsavedname(String attsavedname) {
		this.attsavedname = attsavedname;
	}

	public DocumentEntity getDocument() {
		return document;
	}

	public void setDocument(DocumentEntity document) {
		this.document = document;
	}
	
	
	
	
	
	
}
