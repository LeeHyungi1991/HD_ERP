package hd.erp.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hd.erp.test.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
