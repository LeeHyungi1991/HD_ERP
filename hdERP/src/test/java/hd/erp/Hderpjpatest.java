package hd.erp;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hd.erp.entity.BcommentEntity;
import hd.erp.test.repository.ReplyRepository;

@SpringBootTest
public class Hderpjpatest {

	@Autowired
	ReplyRepository replyrepository;
	
	
	public List<BcommentEntity> asdf (List<BcommentEntity> f,List<BcommentEntity> rereple) {
		//정렬리스트를 받아와서
		List<BcommentEntity> localrereple =rereple;
		int chk =0;
		for(BcommentEntity e : f) {
			//로컬스캐닝
			for(BcommentEntity k : localrereple) {
				if(e.getBcnum() == k.getBcnum()) {
					chk++;
				}
			}
			if(chk == 0) {//발견된게 없다면 추가 하고 트리스캔이 else 일경우는 다음으로 넘어감
				localrereple.add(e);
				for(BcommentEntity j : f) {
					if(e.getBcnum()==j.getBcreply().getBcnum()) {
						localrereple.add(j);
						asdf(f,localrereple);
					}
				}
			}
			//트리스캐닝
			
			
		}
		
		return localrereple;
	}
}
