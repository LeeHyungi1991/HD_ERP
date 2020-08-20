package hd.erp.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity

public class Reply {
	@Id@GeneratedValue
	private int repleid;
	
	private String value;
	
	@JoinColumn(name = "rereply")
	@ManyToOne(targetEntity = Reply.class)
	private Reply rereply;

	public int getRepleid() {
		return repleid;
	}

	public void setRepleid(int repleid) {
		this.repleid = repleid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Reply getRereply() {
		return rereply;
	}

	public void setRereply(Reply rereply) {
		this.rereply = rereply;
	}
	
	
	
}
