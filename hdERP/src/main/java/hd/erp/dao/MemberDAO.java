package hd.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import hd.erp.dto.MemberDTO;

@Repository
@Mapper
public interface MemberDAO {

	List<MemberDTO> memberlist();
}
