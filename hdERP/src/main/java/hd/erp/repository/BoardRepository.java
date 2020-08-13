package hd.erp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hd.erp.entity.BoardEntity;
import hd.erp.entity.EmployeeEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>{
	//리스트 가져오기 페이징 됨
	List<BoardEntity> findAllByOrderByBnumDesc();
	//상세보기할떄?
	BoardEntity findByBnum(Long bnum);
	//이름으로 찾기(페이징 안됨 버리셈)
	List<BoardEntity> findAllByEmployee(EmployeeEntity employee);
	//이름으로 찾기(페이징 됨)
	Page<BoardEntity> findAllByEmployee(EmployeeEntity employee,Pageable pageable);
	
	//제목으로 찾기(페이징 됨)
	Page<BoardEntity> findAllByBtitleLike(String title, Pageable pageable);
	
	
}
