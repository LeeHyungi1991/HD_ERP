package hd.erp.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Entity
@Table(name = "testtable")
@Data
@Alias(value = "test")
public class TestEntitiy {
	@Id@GeneratedValue
	private int testnum;
	
	private String testvarchar;
	@Temporal(TemporalType.DATE)
	private Date testdate;
	
}


