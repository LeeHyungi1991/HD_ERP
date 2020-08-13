package hd.erp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hd.erp.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
	Optional<EmployeeEntity> findByhdcode(Long hd_code);
	
	EmployeeEntity findByHdname(String hdname);

}
