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

	//doc_status �� ����Ʈ �̾ƿ���
	List<DocumentEntity> findBydocstatus(int docstatus,Sort sort);
	//doc_status ����
	List<DocumentEntity> findBydocstatusBetween(int docstatus1,int docstatus2,Sort sort);
	
	//doc_status �� ����Ʈ �̾ƿ��� ����¡ ���� Pageable //sort�� ����� �Ѵ�
	Page<DocumentEntity> findBydocstatus(int docstatus,Pageable pageable);
	
	//doc_status ���� ����¡ ���� //sort�� ����� �Ѵ� //Docdrafter ����
	Page<DocumentEntity> findBydocstatusBetween(int docstatus1,int docstatus2,Pageable pageable);
	
	//doc_status ���� ����¡ ����(���ڰ� �ΰ�) //sort�� ����� �Ѵ� //Docdrafter ����
		Page<DocumentEntity> findByDocdrafterAndDocstatusBetween(EmployeeEntity drafter,int docstatus1,int docstatus2,Pageable pageable);
		
		
		//doc_status(���ڰ� �Ѱ�) �� ����Ʈ �̾ƿ��� ����¡ ���� Pageable //sort�� ����� �Ѵ�//Docdrafter ����
		Page<DocumentEntity> findByDocdrafterAndDocstatus(EmployeeEntity drafter , int docstatus,Pageable pageable);
	
	
	//doc_num Asc�� ���� ÷�� �־�� �Ǵϱ�
	List<DocumentEntity> findAllBydocdrafterOrderByDocnumDesc(EmployeeEntity employee);
}
