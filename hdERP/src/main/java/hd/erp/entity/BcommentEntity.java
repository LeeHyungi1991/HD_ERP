package hd.erp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
@Entity
@Table(name = "bcomment")
@Data
public class BcommentEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bcomment_seq")
	@SequenceGenerator(allocationSize = 1,name = "bcomment_seq",sequenceName = "bcomment_seq")
	@Column(name = "bc_num")
	private Long bcnum;
	
	@JoinColumn(name = "bc_reply")
	@ManyToOne(targetEntity = BcommentEntity.class)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private BcommentEntity bcreply;
	
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
	@OnDelete(action = OnDeleteAction.CASCADE)
	private BoardEntity board;
	
	
	@ManyToOne
	@JoinColumn(name = "employee_hd_code")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private EmployeeEntity employee;
	
	
	
	@Column(name = "bc_depth")
	private Long bcdepth;
	
	
//	@OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "bcreply")
//	private List<BcommentEntity> subcomments;
	
	
//	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,targetEntity = BcommentEntity.class)
//	@JoinColumn(name = "bc_reply")
//	private List<BcommentEntity> children = new ArrayList<BcommentEntity>();
	
}
