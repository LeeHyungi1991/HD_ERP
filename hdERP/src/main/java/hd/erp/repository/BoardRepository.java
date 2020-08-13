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
	//����Ʈ �������� ����¡ ��
	List<BoardEntity> findAllByOrderByBnumDesc();
	//�󼼺����ҋ�?
	BoardEntity findByBnum(Long bnum);
	//�̸����� ã��(����¡ �ȵ� ������)
	List<BoardEntity> findAllByEmployee(EmployeeEntity employee);
	//�̸����� ã��(����¡ ��)
	Page<BoardEntity> findAllByEmployee(EmployeeEntity employee,Pageable pageable);
	
	//�������� ã��(����¡ ��)
	Page<BoardEntity> findAllByBtitleLike(String title, Pageable pageable);
	
	
}
