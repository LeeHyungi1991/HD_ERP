package hd.erp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import hd.erp.dao.TestDAO;
import hd.erp.entity.TestEntitiy;
import hd.erp.repository.TestRepository;
import hd.erp.test.entity.Class;
import hd.erp.test.repository.ClassRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@EnableJpaRepositories
class HdErpApplicationTests {

//	@Autowired
//	hd.erp.repository.ClassRepository classrepository;
//	@Test
//	public void asdf() {
//		
//		hd.erp.entity.Class ref = new hd.erp.entity.Class();
//		ref.setCname("classtest");
//		classrepository.save(ref);
//		
//	}
	
	
	@Autowired
	ClassRepository classrepository;
	//@Test
	public void dfasfd() {
		Class ref = new Class();
		ref.setCname("asdf");
		classrepository.save(ref);
	}
	@Test
	public void aaasdf() {
		System.out.println("asdf");
	}
	
	
	//@Test
	void contextLoads() {
		Document doc = null;
		try {
			doc = Jsoup.connect("https://docs.python.org/3/").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String title = doc.title();
		System.out.println(doc.select("[href]"));

		System.out.println(title);

	}
	//@Test
	public void test() {
		System.out.println("test method");
	}
	
	@Autowired
	TestRepository testrepository;
	
	//@Test
	public void testjpa() {
		TestEntitiy testentity = new TestEntitiy();
		testentity.setTestdate(new Date());
		testentity.setTestvarchar("test1");
		
		testrepository.save(testentity);
	}
	//@Test
	public void listtestjpa() {
		Optional<TestEntitiy> list =testrepository.findById(4);
		TestEntitiy entity= list.get();
		log.info("{},{},{}",entity.getTestdate(),entity.getTestnum(),entity.getTestvarchar());
		//System.out.println(entity.getTestvarchar());
		
	}
	
	//@Test
	public void updatejpa() {
		Optional<TestEntitiy> list =testrepository.findById(4);
		list.ifPresent((select)->{
			select.setTestvarchar("testtest");
			testrepository.save(select);
		});
		
		
		//return list;
	}
	
	
	//@Test
	public void deletejpa() {
		Optional<TestEntitiy> test = testrepository.findById(4);
		test.ifPresent((select)->{testrepository.delete(select);});
	}
	
	@Autowired
	TestDAO testdao;
	
	//@Test
	public void mybatis() {
		List<TestEntitiy> list = testdao.selectTest();
		System.out.println(list.size());
	}
	
	//@Test
	public void mybatis2() {
		TestEntitiy test = new TestEntitiy();
		test.setTestnum(100);
		test.setTestdate(new Date());
		test.setTestvarchar("mybtistest");
		testdao.insertTest(test);
	}
	
	
}
