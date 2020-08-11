package hd.erp.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "book")
@Data
public class Book {
	@Id@Column(name = "no")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer no;
	
	@Column(name = "title",nullable = false,length = 100)
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "category_no")
	private Category category;
}
