package hd.erp.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "book")

public class Book {
	@Id@Column(name = "no")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer no;
	
	@Column(name = "title",nullable = false,length = 100)
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "category_no")
	private Category category;

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
}
