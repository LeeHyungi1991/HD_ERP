package hd.erp.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hd.erp.test.entity.Class;
@Repository
public interface ClassRepository extends JpaRepository<Class, Long>{

}
