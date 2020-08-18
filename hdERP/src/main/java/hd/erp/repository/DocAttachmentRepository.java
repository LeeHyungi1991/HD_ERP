package hd.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hd.erp.entity.DocAttachmentEntity;

@Repository
public interface DocAttachmentRepository  extends JpaRepository<DocAttachmentEntity, Long>{

}
