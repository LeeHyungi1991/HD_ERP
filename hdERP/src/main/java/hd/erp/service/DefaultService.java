package hd.erp.service;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hd.erp.config.ApplicationYamlRead;
import hd.erp.dto.EmployeeDTO;
import hd.erp.entity.EmployeeEntity;
import hd.erp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DefaultService {

	
	@Autowired
	ApplicationYamlRead applicationyamlread;
	
	@Autowired
	EmployeeService employeeservice;
	
	@Autowired
	EmployeeRepository employeerepository;
	//ȸ������
	public void register(EmployeeDTO employeedto) {
		employeeservice.register(employeedto);
	}
	
	
	
	
	public EmployeeEntity findindexname(Long hdcode) {
		Optional<EmployeeEntity> emp = employeerepository.findById(hdcode);
		return emp.get();
	}
	
	
	
	
	
	public EmployeeEntity getuserprofile(Long hd_code) {
		Optional<EmployeeEntity> emp = employeerepository.findByhdcode(hd_code);
		return emp.get();
		
	}
	
	
	//���������� ����
	public void uploadprofile(EmployeeEntity employee,Long hdcode) {
		System.out.println(">>>>>>>>>>>>>>>");
		System.out.println(employee.getHdcode());
		System.out.println(employee.getHdadminpw());
		System.out.println(employee.getHdpw());
		System.out.println(employee.getHdlevel());
		System.out.println("-------------------");
		System.out.println(employee.getHdbirth());
		System.out.println(employee.getHdphn());
		System.out.println(employee.getHdloc());
		System.out.println(employee.getHdemail());
		System.out.println(employee.getHdgender());
		System.out.println(employee.getHdname());
		System.out.println(employee.getHddname());
		System.out.println(employee.getHdindate());
		
		
		System.out.println("<<<<<<<<<<<<<<<<<");
		
		EmployeeEntity myemp = employeerepository.findByhdcode(hdcode).get();
		myemp.setHdbirth(employee.getHdbirth());
		myemp.setHdphn(employee.getHdphn());
		myemp.setHdloc(employee.getHdloc());
		myemp.setHdemail(employee.getHdemail());
		myemp.setHdgender(employee.getHdgender());
		myemp.setHdname(employee.getHdname());
		myemp.setHddname(employee.getHddname());
		myemp.setHdindate(employee.getHdindate());
		
		employeerepository.save(myemp);
		
//		employeerepository.save(employee);
		
	}
	
	
	
	
	
	
	public String userprofileimgupload(MultipartFile mfile,Principal principal) {
//		System.out.println(mfile.getName());
//		System.out.println(mfile.getContentType());
//		System.out.println(mfile.getOriginalFilename());
//		System.out.println(mfile.getSize());
		
		String statipath = applicationyamlread.getPath();
		
		String path = statipath+"\\img\\"+principal.getName(); //���� ���
		File Folder = new File(path);
		
		
		 //�ش� ���丮�� ������� ���丮�� �����մϴ�.
		if (!Folder.exists()) {
			try{
			    Folder.mkdir(); //���� �����մϴ�.
			    System.out.println("������ �����Ǿ����ϴ�.");
			    System.out.println(Folder.getPath());
		        } 
		        catch(Exception e){
			    e.getStackTrace();
			}        
	         }else {
			System.out.println("�̹� ������ �����Ǿ� �ֽ��ϴ�.");
		}
		
		
		
		
		if(mfile != null) {
			try {
				mfile.transferTo(new File(path+"\\"+"profile.png"));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.info("path�� {}",path.toString()+"\\"+"profile.png");
		
		return path.toString()+"\\"+"profile.png";
		
		
		
//		HttpSession session = request.getSession();
//		String r_path=session.getServletContext().getRealPath("/");
//		System.out.println("RPATH="+r_path);
//		String r_path2=session.getServletContext().getRealPath("");
//		System.out.println("RPATH2="+r_path2);
//
//
//		
//		//�̹��� �̸��� ����
//		String oriFn =mfile.getOriginalFilename(); //���ε� �� �̹��� �̸�
//		
//		System.out.println("FullPath2 : "+r_path+oriFn+"\\upload\\");//���� �̹����� ����� ���
//		
//		
//		
//		File Folder2 = new File(r_path+"\\upload\\");
//		
//		
//		 //�ش� ���丮�� ������� ���丮�� �����մϴ�.
//		if (!Folder2.exists()) {
//			try{
//			    Folder2.mkdir(); //���� �����մϴ�.
//			    System.out.println("������ �����Ǿ����ϴ�.");
//			    System.out.println(Folder2.getPath());
//		        } 
//		        catch(Exception e){
//			    e.getStackTrace();
//			}        
//	         }else {
//			System.out.println("�̹� ������ �����Ǿ� �ֽ��ϴ�.");
//		}
//		
//		
//		File f = new File(Folder2.getPath()+"//"+oriFn);
//		try {
//			mfile.transferTo(f); //�������� transferTo�� ȣ���ؼ� �̹����� ������ҿ� ����
//		} catch (IllegalStateException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		
//		System.out.println("rPath : "+r_path);
//		String img_path ="static\\img";
//		System.out.println("imgPath : "+r_path);
//		StringBuffer path= new StringBuffer();
//		path.append(r_path).append(img_path);
//		
//		System.out.println("FullPath : "+path);//���� �̹����� ����� ���
//		//���� ���ε� ����
//		
//		File Folder = new File(path.toString());
//		 //�ش� ���丮�� ������� ���丮�� �����մϴ�.
//		if (!Folder.exists()) {
//			try{
//			    Folder.mkdir(); //���� �����մϴ�.
//			    System.out.println("������ �����Ǿ����ϴ�.");
//			    System.out.println(Folder.getPath());
//		        } 
//		        catch(Exception e){
//			    e.getStackTrace();
//			}        
//	         }else {
//			System.out.println("�̹� ������ �����Ǿ� �ֽ��ϴ�.");
//		}
//		
//		
//		//�̹��� �̸��� ����
//		String oriFn = "\\"+mfile.getOriginalFilename(); //���ε� �� �̹��� �̸�
//		path.append(oriFn);
//		System.out.println("FullPath2 : "+path);//���� �̹����� ����� ���
//		
//		
//		File f = new File(path.toString());
//		try {
//			mfile.transferTo(f); //�������� transferTo�� ȣ���ؼ� �̹����� ������ҿ� ����
//		} catch (IllegalStateException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		m.addAttribute("path", path);
	}




	
	
}
