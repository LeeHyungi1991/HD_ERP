package hd.erp.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "category")
@Data
public class Category {
	@Id@Column(name = "no")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer no;
	
	@Column(name = "name",nullable = false, length = 100)
	private String name;
	
	

}
