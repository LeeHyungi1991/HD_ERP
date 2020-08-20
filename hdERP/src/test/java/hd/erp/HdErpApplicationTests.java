package hd.erp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import hd.erp.dao.TestDAO;
import hd.erp.entity.TestEntitiy;
import hd.erp.repository.TestRepository;
import hd.erp.test.entity.Book;
import hd.erp.test.entity.Category;
import hd.erp.test.entity.Class;
import hd.erp.test.repository.BookRepository;
import hd.erp.test.repository.CategoryRepository;
import hd.erp.test.repository.ClassRepository;
import hd.erp.test.repository.StudentRepository;



@SpringBootTest
@EnableJpaRepositories
class HdErpApplicationTests {
	
	
	private static final Logger log = LoggerFactory.getLogger(HdErpApplicationTests.class);

	
	@Autowired
	ClassRepository classrepository;
	
	@Autowired
	StudentRepository studentrepository;
	
	//@Test
	public void jpajoincrud() {
		System.out.println("a");
//		Scanner scanner = new Scanner(System.in);
//		String a =scanner.next();
//		System.out.println(a);
//		int i =1;
//		while(1<5) {
//			System.out.println("asdf");
//			String a =scanner.next();
//			System.out.println(a);
//			i++;
//		}
		//scanner.close();
	}
	
	
	
	
	
	

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
	BookRepository bookrepository;
	
	@Autowired
	CategoryRepository categoryrepository;
	//@Test
	public void asdsd() {
		Category cate1 = new Category();
		cate1.setName("cate1123");
		Category cate2 = new Category();
		cate2.setName("cate2123");
		
		categoryrepository.save(cate1);
		
		Optional<Category> asdf = categoryrepository.findById(23);
		Category aaaa =asdf.get();
		

		System.out.println(aaaa.getName());
		System.out.println(aaaa.getNo());
				
		
		Book book1 = new Book();
		book1.setTitle("booktitle?a?a?");
		book1.setCategory(aaaa);
		bookrepository.save(book1);
		
		
		List<Book> booklist =bookrepository.findAll();
		for(Book e : booklist) {
			log.info("bookno={},booktitle={},categoryname={},categoryno{}",e.getNo(),e.getTitle(),e.getCategory().getName(),e.getCategory().getNo());
		}
		
		System.out.println("testrelation");
	}
	
	
	
	
//	@Autowired
//	ClassRepository classrepository;
	//@Test
	public void dfasfd() {
		Class ref = new Class();
		ref.setCname("asdf");
		classrepository.save(ref);
	}
	//@Test
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
