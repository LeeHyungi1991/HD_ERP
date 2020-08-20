package hd.erp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hd.erp.entity.DocumentEntity;
import hd.erp.entity.EmployeeEntity;


@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long>{

	//doc_status 로 리스트 뽑아오기
	List<DocumentEntity> findBydocstatus(int docstatus,Sort sort);
	//doc_status 범위
	List<DocumentEntity> findBydocstatusBetween(int docstatus1,int docstatus2,Sort sort);
	
	//doc_status 로 리스트 뽑아오기 페이징 버전 Pageable //sort가 없어야 한다
	Page<DocumentEntity> findBydocstatus(int docstatus,Pageable pageable);
	
	//doc_status 범위 페이징 버전 //sort가 없어야 한다 //Docdrafter 붙음
	Page<DocumentEntity> findBydocstatusBetween(int docstatus1,int docstatus2,Pageable pageable);
	
	//doc_status 범위 페이징 버전(인자값 두개) //sort가 없어야 한다 //Docdrafter 붙음
		Page<DocumentEntity> findByDocdrafterAndDocstatusBetween(EmployeeEntity drafter,int docstatus1,int docstatus2,Pageable pageable);
		
		
		//doc_status(인자값 한개) 로 리스트 뽑아오기 페이징 버전 Pageable //sort가 없어야 한다//Docdrafter 붙음
		Page<DocumentEntity> findByDocdrafterAndDocstatus(EmployeeEntity drafter , int docstatus,Pageable pageable);
	
	
	//doc_num Asc로 파일 첨부 넣어야 되니까
	List<DocumentEntity> findAllBydocdrafterOrderByDocnumDesc(EmployeeEntity employee);
}
