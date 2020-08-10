package hd.erp.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "class")
public class Class {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "class_seq")
	@SequenceGenerator(name ="class_seq",allocationSize = 1,sequenceName = "class_seq" )
	@Column(name = "c_num")
	private Long cnum;
	@Column(name = "c_name")
	private String cname;
}
