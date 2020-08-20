package hd.erp.test.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity

@Table(name = "class")
public class Class {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "class_seq")
	@SequenceGenerator(name ="class_seq",allocationSize = 1,sequenceName = "class_seq" )
	@Column(name = "c_num")
	private Long cnum;
	
	@Column(name = "c_name")
	private String cname;
	
	@Column(name = "c_max")
	private int cmax;
	
	@Column(name = "c_date")
	private Date cdate;

	public Long getCnum() {
		return cnum;
	}

	public void setCnum(Long cnum) {
		this.cnum = cnum;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getCmax() {
		return cmax;
	}

	public void setCmax(int cmax) {
		this.cmax = cmax;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	
//	@OneToMany(mappedBy = "cnum")
//	public List<Student> students = new ArrayList<Student>();
	
	
	
}
