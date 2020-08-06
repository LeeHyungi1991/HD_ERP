package hd.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import hd.erp.entity.TestEntitiy;
@Repository
@Mapper
public interface TestDAO {

	List<TestEntitiy> selectTest();
	void insertTest(TestEntitiy test);
}
