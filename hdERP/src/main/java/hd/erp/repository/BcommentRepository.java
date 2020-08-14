package hd.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hd.erp.entity.BcommentEntity;

@Repository
public interface BcommentRepository extends JpaRepository<BcommentEntity,Long>{

}
