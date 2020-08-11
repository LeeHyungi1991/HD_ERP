package hd.erp.test.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "student")
public class Student {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "student_seq")
	@SequenceGenerator(name ="student_seq",allocationSize = 1,sequenceName = "student_seq" )
	@Column(name = "s_num")
	private Long snum;
	
	@Column(name = "s_name")
	private String sname;
	
	@Column(name = "s_gender")
	private String sgender;
	
	//@Column(name = "c_num")
	@JoinColumn(name = "class_c_num")
	@ManyToOne
	private Class cnum;

}
