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
		//���ĸ���Ʈ�� �޾ƿͼ�
		List<BcommentEntity> localrereple =rereple;
		int chk =0;
		for(BcommentEntity e : f) {
			//���ý�ĳ��
			for(BcommentEntity k : localrereple) {
				if(e.getBcnum() == k.getBcnum()) {
					chk++;
				}
			}
			if(chk == 0) {//�߰ߵȰ� ���ٸ� �߰� �ϰ� Ʈ����ĵ�� else �ϰ��� �������� �Ѿ
				localrereple.add(e);
				for(BcommentEntity j : f) {
					if(e.getBcnum()==j.getBcreply().getBcnum()) {
						localrereple.add(j);
						asdf(f,localrereple);
					}
				}
			}
			//Ʈ����ĳ��
			
			
		}
		
		return localrereple;
	}
}
