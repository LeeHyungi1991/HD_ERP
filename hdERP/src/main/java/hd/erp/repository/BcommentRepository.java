package hd.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hd.erp.entity.BcommentEntity;
import hd.erp.entity.BoardEntity;

@Repository
public interface BcommentRepository extends JpaRepository<BcommentEntity,Long>{
	//�Խ��� ��ȣ�� �Խ��� ��۰�������
	List<BcommentEntity> findByBoard(BoardEntity board);
	//�Խ��� �ۼ����� �ؾ��� ��
	List<BcommentEntity> findAllByBoardOrderByBcnumAsc(BoardEntity board);
}
