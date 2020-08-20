package hd.erp.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "board")
//@Data ทาบน คะ
public class BoardEntity {
	/*
create sequence board_seq
start with 1
INCREMENT by 1;
	 * */
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

	public Long getBnum() {
		return bnum;
	}

	public void setBnum(Long bnum) {
		this.bnum = bnum;
	}

	public Long getBhit() {
		return bhit;
	}

	public void setBhit(Long bhit) {
		this.bhit = bhit;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public Long getBlike() {
		return blike;
	}

	public void setBlike(Long blike) {
		this.blike = blike;
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}
	
//	@OneToMany(mappedBy = "boardentity")
//	private List<BcommentEntity> bcommentlist = new ArrayList<BcommentEntity>();

	
	
	
	
	
}
