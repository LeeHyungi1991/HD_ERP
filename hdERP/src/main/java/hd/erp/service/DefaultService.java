package hd.erp.service;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hd.erp.config.ApplicationYamlRead;
import hd.erp.dto.EmployeeDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DefaultService {

	
	@Autowired
	ApplicationYamlRead applicationyamlread;
	
	@Autowired
	EmployeeService employeeservice;
	
	public void register(EmployeeDTO employeedto) {
		employeeservice.register(employeedto);
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
		
		
		
		
		
		try {
			mfile.transferTo(new File(path+"\\"+"profile.png"));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
