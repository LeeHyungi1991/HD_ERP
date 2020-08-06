package hd.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hd.erp.entity.TestEntitiy;

@Repository
public interface TestRepository extends JpaRepository<TestEntitiy, Integer>{
	
	
	

}
