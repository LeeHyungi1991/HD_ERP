package hd.erp.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hd.erp.test.entity.Homework;
@Repository
public interface HomeworkRepository extends JpaRepository<Homework, Long>{

}
