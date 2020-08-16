package hd.erp.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Reply {
	@Id@GeneratedValue
	private int repleid;
	
	private String value;
	
	@JoinColumn(name = "rereply")
	@ManyToOne(targetEntity = Reply.class)
	private Reply rereply;
}
