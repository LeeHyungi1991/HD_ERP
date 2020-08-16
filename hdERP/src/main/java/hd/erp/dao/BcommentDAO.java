package hd.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import hd.erp.dto.BcommentDTO;
import hd.erp.entity.BcommentEntity;

@Repository
@Mapper
public interface BcommentDAO {

	List<BcommentDTO> hierarchical(Long bnum);
}
