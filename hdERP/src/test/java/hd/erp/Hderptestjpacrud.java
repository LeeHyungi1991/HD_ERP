package hd.erp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import hd.erp.test.entity.Class;
import hd.erp.test.entity.Homework;
import hd.erp.test.entity.Student;
import hd.erp.test.repository.ClassRepository;
import hd.erp.test.repository.HomeworkRepository;
import hd.erp.test.repository.StudentRepository;

@SpringBootTest
public class Hderptestjpacrud {
	
	@Autowired
	ClassRepository classrepository;
	
	@Autowired
	StudentRepository studentrepository;
	
	@Autowired
	HomeworkRepository homeworkrepository;
	
	
	
	@Test
	public void jpajoin() {
		
		Scanner sc = new Scanner(System.in);
		
		xx:while(true) {
			System.out.println("1:���, 2:�Է� ,3:����  ,4:����, 5:����");
			String choose1=sc.next();
			if(choose1.equals("1")) {
				System.out.println("���");
				System.out.println("1 : Class , 2 :Student, 3: Homework ,6:����¡�׽�Ʈ");
				String choose2 = sc.next();
				if(choose2.equals("1")) {
					System.out.println("Class//");
					List<Class> classlist=classrepository.findAll();
					for(Class e : classlist) {
						System.out.println("num >> "+e.getCnum()+", name >> "+e.getCname()+", max >> "+e.getCmax()+", date >>"+e.getCdate());
						System.out.println("============================");
					}
					
				}else if(choose2.equals("2")) {
					System.out.println("Student//");
					List<Student> studentlist =studentrepository.findAll();
					for (Student e : studentlist) {
						System.out.println("num >> "+e.getSnum()+", name >> "+e.getSname()+", gender >> "+e.getSgender()+", fk_class_num >>"+e.getCnum());
						System.out.println("============================");
					}
				}else if(choose2.equals("3")) {
					System.out.println("Homework//");
					List<Homework> homeworklist =homeworkrepository.findAll();
					for (Homework e : homeworklist) {
						System.out.println("num >> "+e.getHnum()+", name >>"+e.getHname()+", fk_student_num >> "+e.getSnum()+", hdate >>"+e.getHdate());
						System.out.println("=============================");
						
					}
				}else if(choose2.equals("4")) {
					System.out.println("���ϴ� ���� �л�, �ݹ�ȣ =");
					String cnum = sc.next();
					Optional<Class> cl =classrepository.findById(Long.parseLong(cnum));
					Class cll = cl.get();
					List<Student> stlist = studentrepository.findByCnum(cll);
					for(Student e : stlist) {
						System.out.println(e.getSname());
					}
				}else if(choose2.equals("5")) {
//					Class clas=new Class();
//					List<Student> stlist =clas.getStudents();
//					System.out.println(stlist.size());
				}else if(choose2.equals("6")) {
//					//������ �׽�Ʈ
						Page<Student> stpage = studentrepository.findAll(PageRequest.of(5, 1));//page,size
						List<Student> stlit =stpage.getContent();
						System.out.println(stlit.size());
						//System.out.println(stpage.get);
						
						System.out.println(stpage.toString());
						System.out.println(stlit.toString());
						for (Student e : stlit) {
							System.out.println(e.getSname());
						}
						


				}else {
					System.out.println("����");
					break xx;
				}
				
				
				
				
				
			}else if(choose1.equals("2")) {
				System.out.println("�Է�");
				System.out.println("1 : Class , 2 :Student, 3: Homework");
				String choose2 = sc.next();
				if(choose2.equals("1")) {
					System.out.println("class//");
					System.out.println("class name �Է� :");
					String c_name=sc.next();
					System.out.println("class max �Է� :");
					String c_max =sc.next();
					System.out.println("������)class date �Է�");
					String cdate = sc.next();
					
					SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
					
					Class cl1=new Class();
					cl1.setCname(c_name);
					cl1.setCmax(Integer.parseInt(c_max));
					try {
						cl1.setCdate(sdf.parse(cdate));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					classrepository.save(cl1);
					System.out.println("�Է¿Ϸ�");
					
				}else if(choose2.equals("2")) {
					System.out.println("student//");
					System.out.println("student name �Է� : ");
					String sname = sc.next();
					System.out.println("student gender �Է� : ");
					String sgender = sc.next();
					List<Class> classlist=classrepository.findAll();
					for(Class e : classlist) {
						System.out.println("�ݹ�ȣ >> " +e.getCnum());
						System.out.println("���̸� >> " +e.getCname());
						System.out.println("======================");
					}
					System.out.println("�Ҽӵ� class ��ȣ �Է�");
					String sclass = sc.next();
					Optional<Class> asdf = classrepository.findById(Long.parseLong(sclass));
					Class cl = asdf.get();
					
					Student st1 = new Student();
					st1.setSname(sname);
					st1.setSgender(sgender);
					st1.setCnum(cl);
					
					try {
						studentrepository.save(st1);
					} catch (Exception e) {
						e.printStackTrace();
						jpajoin();
					}
					
					
				}else if(choose2.equals("3")) {
					System.out.println("homework//");
					System.out.println("homework name �Է� : ");
					String hname = sc.next();
					
					List<Student> studentlist=studentrepository.findAll();
					for(Student e : studentlist) {
						System.out.println("�л���ȣ >> " +e.getSnum());
						System.out.println("�л��̸� >> " +e.getSname());
						System.out.println("======================");
					}
					System.out.println("� �л��� �����ΰ�");
					String hstudent = sc.next();
					Optional<Student> asdf = studentrepository.findById(Long.parseLong(hstudent));
					Student stu = asdf.get();
					
					Homework ho1 = new Homework();
					ho1.setHname(hname);
					ho1.setSnum(stu);
					ho1.setHdate(new Date());
					
					try {
						homeworkrepository.save(ho1);
					} catch (Exception e) {
						e.printStackTrace();
						jpajoin();
					}
					
				}else {
					System.out.println("����");
					break xx;
				}
				
				
				
				
				
				
				
				
			}else if(choose1.equals("4")) {
				System.out.println("����");
				System.out.println("1 : Class , 2 :Student, 3: Homework");
				String chooes2=sc.next();
				if(chooes2.equals("1")) {
					System.out.println("..Class");
					List<Class> classlist=classrepository.findAll();
					for(Class e : classlist) {
						System.out.println("num >> "+e.getCnum()+", name >> "+e.getCname()+", max >> "+e.getCmax()+", date >>"+e.getCdate());
						System.out.println("============================");
					}
					System.out.println("�������ϴ� ��ȣ��?");
					String delnum = sc.next();
					classrepository.deleteById(Long.parseLong(delnum));
				}else if(chooes2.equals("2")) {
					System.out.println("..Student");
					List<Student> stlist=studentrepository.findAll();
					for(Student e : stlist) {
						System.out.println("num >> "+e.getSnum()+", name >> "+e.getSname()+", gender >> "+e.getSgender()+", fk_class_num >>"+e.getCnum());
						System.out.println("============================");
					}
					System.out.println("�������ϴ� ��ȣ��?");
					String delnum = sc.next();
					studentrepository.deleteById(Long.parseLong(delnum));
				}else if(chooes2.equals("3")) {
					System.out.println("..Homework");
					List<Homework> hwlist=homeworkrepository.findAll();
					for(Homework e : hwlist) {
						System.out.println("num >> "+e.getHnum()+", name >>"+e.getHname()+", fk_student_num >> "+e.getSnum()+", hdate >>"+e.getHdate());
						System.out.println("=============================");
					}
					System.out.println("�������ϴ� ��ȣ��?");
					String delnum = sc.next();
					homeworkrepository.deleteById(Long.parseLong(delnum));
				}else {
					
				}
				
				
			}else if(choose1.equals("5")) {
				System.out.println("����");
				System.out.println("1 : Class , 2 :Student, 3: Homework");
				String choose2 = sc.next();
				
				if(choose2.equals("1")) {
					List<Class> classlist=classrepository.findAll();
					for(Class e : classlist) {
						System.out.println("num >> "+e.getCnum()+", name >> "+e.getCname()+", max >> "+e.getCmax()+", date >>"+e.getCdate());
						System.out.println("============================");
					}
					System.out.println("���ϴ� ���� ��ȣ��?");
					String upnum = sc.next();
					
					System.out.println("class name �Է� :");
					String c_name=sc.next();
					System.out.println("class max �Է� :");
					String c_max =sc.next();
					System.out.println("������)class date �Է�");
					String cdate = sc.next();
					
					SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
					
					
					Class cl123= new Class();
					cl123.setCnum(Long.parseLong(upnum));
					cl123.setCname(c_name);
					cl123.setCmax(Integer.parseInt(c_max));
					try {
						cl123.setCdate(sdf.parse(cdate));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					classrepository.save(cl123);
					
				}else if(choose2.equals("2")) {
					
				}else if(choose2.equals("3")) {
					
				}else {
					
				}
				
			}else {
				break xx;
			}
		}
	}
}
