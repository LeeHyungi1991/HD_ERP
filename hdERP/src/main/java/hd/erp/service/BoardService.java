package hd.erp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hd.erp.entity.BoardEntity;
import hd.erp.entity.EmployeeEntity;
import hd.erp.repository.BoardRepository;
import hd.erp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardService {

	@Autowired
	EmployeeRepository employeerepository;
	
	@Autowired
	BoardRepository boardrepository;
	
	public void insertboard(BoardEntity board,Long hdcode) {
		log.info("누구인가요? {} 입니다",hdcode);
		Optional<EmployeeEntity> emp = employeerepository.findById(hdcode);
		EmployeeEntity employee = emp.get();
		
		board.setEmployee(employee);
		board.setBdate(new Date());
		board.setBhit(0L);
		board.setBlike(0L);
		
		boardrepository.save(board);
		
	}
	
	
	public List<BoardEntity> boardlist(){
		return boardrepository.findAllByOrderByBnumDesc();
	}
	
	public BoardEntity boarddetail(Long bnum) {
		return boardrepository.findByBnum(bnum);
	}
}


