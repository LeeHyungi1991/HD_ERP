package hd.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hd.erp.entity.DocAttachmentEntity;
import hd.erp.entity.DocumentEntity;

@Repository
public interface DocAttachmentRepository  extends JpaRepository<DocAttachmentEntity, Long>{

	List<DocAttachmentEntity> findByDocument(DocumentEntity document);
}
