package hd.erp.test.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

//@Entity
@Data
public class Student {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "student_seq")
	@SequenceGenerator(name ="student_seq",allocationSize = 1,sequenceName = "student_seq" )
	@Column(name = "s_num")
	private Long snum;
	@Column(name = "s_name")
	private String sname;
	@Column(name = "c_name")
	private List<Class> cnum;

}
