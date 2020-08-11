package hd.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hd.erp.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>{

	List<BoardEntity> findAllByOrderByBnumDesc();
	BoardEntity findByBnum(Long bnum);

	
}
