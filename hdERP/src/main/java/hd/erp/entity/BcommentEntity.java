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
@Table(name = "bcomment")
@Data
public class BcommentEntity {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bcomment_seq")
	@SequenceGenerator(allocationSize = 1,name = "bcomment_seq",sequenceName = "bcomment_seq")
	@Column(name = "bc_num")
	private Long bcnum;
	
	@Column(name = "bc_reply")
	private Long bcreply;
	
	@Column(name = "bc_writer")
	private String bcwriter;
	
	@Column(name = "bc_content")
	@Lob
	private String bccontent;
	
	@Column(name = "bc_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bcdate;
	
	@ManyToOne
	@JoinColumn(name = "board_b_num")
	private BoardEntity board;
}