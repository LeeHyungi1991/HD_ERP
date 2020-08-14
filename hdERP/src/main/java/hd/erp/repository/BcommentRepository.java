package hd.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hd.erp.entity.BcommentEntity;
import hd.erp.entity.BoardEntity;

@Repository
public interface BcommentRepository extends JpaRepository<BcommentEntity,Long>{
	//게시판 번호로 게시판 댓글가져오기
	List<BcommentEntity> findByBoard(BoardEntity board);
	//게시판 글순으로 해야지 ㅠ
	List<BcommentEntity> findAllByBoardOrderByBcnumAsc(BoardEntity board);
}
