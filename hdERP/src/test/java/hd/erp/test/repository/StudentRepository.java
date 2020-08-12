package hd.erp.test.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hd.erp.test.entity.Class;
import hd.erp.test.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student> findByCnum(Class num);
	
}
