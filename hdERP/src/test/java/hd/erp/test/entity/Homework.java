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
public class Homework {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "homework_seq")
	@SequenceGenerator(name ="homework_seq",allocationSize = 1,sequenceName = "homework_seq" )
	@Column(name = "h_num")
	private Long hnum;
	@Column(name = "h_name")
	private String hname;
	@Column(name = "s_num")
	private List<Student> snum;
}
