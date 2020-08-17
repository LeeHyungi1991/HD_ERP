package hd.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hd.erp.entity.DocumentEntity;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long>{

	//doc_status �� ����Ʈ �̾ƿ���
	List<DocumentEntity> findBydocstatus(int docstatus);
	//doc_status ����
	List<DocumentEntity> findBydocstatusBetween(int docstatus1,int docstatus2);
}
