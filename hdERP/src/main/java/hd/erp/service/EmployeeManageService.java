package hd.erp.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hd.erp.config.ApplicationYamlRead;
import hd.erp.entity.BoardEntity;
import hd.erp.entity.DocAttachmentEntity;
import hd.erp.entity.DocumentEntity;
import hd.erp.entity.EmployeeEntity;
import hd.erp.repository.DocAttachmentRepository;
import hd.erp.repository.DocumentRepository;
import hd.erp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class EmployeeManageService {
	private static final int BUFFER_SIZE = 4068;
	@Autowired
	ApplicationYamlRead applicationyamlread;
	
	@Autowired
	EmployeeRepository employeerepository;
	
	@Autowired
	DocumentRepository documentrepository;
	
	@Autowired
	DocAttachmentRepository docattachmentrepository;
	
	//�������Ʈ ��������
	public List<EmployeeEntity> getemplist(){
		List<EmployeeEntity> emplist = employeerepository.findAll();
		
		return emplist;
	}
	
	
	
	//����ϱ� �Ҷ� ����Ʈ �ڽ��� ���� �����
	public Map<String, List<EmployeeEntity>> emplist() {
		Map<String, List<EmployeeEntity>> emplist = new HashMap<String, List<EmployeeEntity>>();
		List<EmployeeEntity> adminemplist = employeerepository.findByhdlevel("ADMIN");
		List<EmployeeEntity> memberemplist = employeerepository.findByhdlevel("MEMBER");
		List<EmployeeEntity> useremplist = employeerepository.findByhdlevel("USER");
		System.out.println("adminemplist >"+adminemplist.size());
		System.out.println("memberemplist >"+memberemplist.size());
		System.out.println("useremplist >"+useremplist.size());
		emplist.put("adminlist", adminemplist);
		emplist.put("memberlist", memberemplist);
		emplist.put("userlist", useremplist);
		return emplist;
		
	}
	//�� ������������ ����ϱ⿡ ��
	public EmployeeEntity getmylevel(Long hd_code) {
		Optional<EmployeeEntity> empopt = employeerepository.findByhdcode(hd_code);
		EmployeeEntity emp = empopt.get();
		String mylevel = emp.getHdlevel();
		System.out.println("mylevel >>"+mylevel);
		
		return emp;
	}
	
	//����ϱ� ������ ����
	public void documentinsert(DocumentEntity document, String emp1,String emp2, String emp3, String mynum,MultipartFile[] file) {
		System.out.println("����");
		DocumentEntity doc = new DocumentEntity();
		EmployeeEntity appemp1 =null;
		EmployeeEntity appemp2=null;
		EmployeeEntity appemp3=null;
		if(emp1 != null) {
		appemp1 = employeerepository.findByhdcode(Long.parseLong(emp1)).get();
		}
		if(emp2 != null){
		appemp2 = employeerepository.findByhdcode(Long.parseLong(emp2)).get();
		}
		if(emp3 !=null) {
		appemp3 = employeerepository.findByhdcode(Long.parseLong(emp3)).get();
		}
		System.out.println("����123");
		EmployeeEntity myemp = employeerepository.findByhdcode(Long.parseLong(mynum)).get();
		System.out.println("--=-=-=-==-");
		doc.setDocdate(new Date());
		doc.setDocfirstemp(appemp1);
		doc.setDocsecondemp(appemp2);
		doc.setDocthirdemp(appemp3);
		doc.setDocdrafter(myemp);
		System.out.println(myemp);
		if(myemp.getHdlevel().equals("ADMIN")) {
			doc.setDocstatus(2);
		}else if(myemp.getHdlevel().equals("MEMBER")) {
			doc.setDocstatus(1);
		}else if(myemp.getHdlevel().equals("USER")) {
			doc.setDocstatus(0);
		}else {
			doc.setDocstatus(0);
		}
		
		
		doc.setDoctitle(document.getDoctitle());
		doc.setDoccontent(document.getDoccontent());
		
		doc.setDoc1ignorecomment(null);
		doc.setDoc2ignorecomment(null);
		doc.setDoc3ignorecomment(null);
		
		doc.setDoc1okcomment(null);
		doc.setDoc2okcomment(null);
		doc.setDoc3okcomment(null);
		System.out.println("������ doc num" + document.getDocnum());
		System.out.println("������ doc status "+document.getDocstatus());
		//�����Ǹ� ���ϳ� �����ؾߵǴµ�
		if(document.getDocstatus() == -1) {
			doc.setDocnum(document.getDocnum());
		}
		documentrepository.save(doc);
		System.out.println("���� ����");
		log.info("���� ����{}",doc.getDocnum());
		
		if(file[0].getSize() != 0) {
			
			for(int i = 0; i<file.length;i++) {
				DocAttachmentEntity attachment = new DocAttachmentEntity();
				
				
				System.out.println(file[i].getOriginalFilename());
				String staticpath =applicationyamlread.getPath();
				
				String fileRoot =staticpath+"\\document\\"+mynum+"\\attachment";
				String originalFileName = file[i].getOriginalFilename();
				String extension =originalFileName.substring(originalFileName.lastIndexOf("."));
				String savedFileName =UUID.randomUUID()+extension;
				File targetFile = new File(fileRoot);
				
				
					
					 //�ش� ���丮�� ������� ���丮�� �����մϴ�.
					if (!targetFile.exists()) {
						try{
							targetFile.mkdirs(); //���� �����մϴ�.
						    System.out.println("������ �����Ǿ����ϴ�.");
						    System.out.println(targetFile.getPath());
					        } 
					        catch(Exception e){
						    e.getStackTrace();
						}        
				         }else {
						System.out.println("�̹� ������ �����Ǿ� �ֽ��ϴ�.");
					}
					
					
					
					
					File targetFile2 = new File(fileRoot);
					try {
						file[i].transferTo(new File(targetFile2.getPath()+"\\"+originalFileName));
						
						
					} catch (IllegalStateException | IOException e) {
						// TODO Auto-generated catch block
						
						e.printStackTrace();
					}
					
					attachment.setAttname(originalFileName);
					attachment.setAttsavedname(originalFileName);////////////////
					attachment.setDocument(doc);
					docattachmentrepository.save(attachment);
				
				
			}
		}
		
		
	}
	//���� �Ⱒ������
	public void docignore(Long docnum, DocumentEntity document, Long hdcode,String ignorecomment) {
		EmployeeEntity emp = employeerepository.findByhdcode(hdcode).get();
		DocumentEntity mydoc = documentrepository.findById(docnum).get();
		int docstatus = document.getDocstatus();
		if(docstatus == 0) {//1�����簡�� �Ⱒ
			mydoc.setDoc1ignorecomment(ignorecomment);
		}else if (docstatus == 1) {//2�����簡�� �Ⱒ
			mydoc.setDoc2ignorecomment(ignorecomment);
		}else if(docstatus == 2) {//3�����簡�� �Ⱒ
			mydoc.setDoc3ignorecomment(ignorecomment);
		}
		//���� �ڸ�Ʈ�� ����
		//mydoc.setDoc1okcomment(null);
		//mydoc.setDoc2okcomment(null);
		//mydoc.setDoc3okcomment(null);
		mydoc.setDocignorestatus(String.valueOf(docstatus));
		mydoc.setDocstatus(-1);
		mydoc.setDocignoreemp(emp);
		documentrepository.save(mydoc);
	}
	//���� ���� ������
	public void docok(DocumentEntity document,String okcomment) {
		DocumentEntity mydoc = documentrepository.findById(document.getDocnum()).get();
		int docstatus = document.getDocstatus();
		if(docstatus ==0) { //1�������� ����
			mydoc.setDoc1okcomment(okcomment);
		}else if(docstatus ==1) {//2�� ������ ����
			mydoc.setDoc2okcomment(okcomment);
		}else if(docstatus ==2) {//3�� ������ ����
			mydoc.setDoc3okcomment(okcomment);
		}
		//������� �ö�
		mydoc.setDocstatus(document.getDocstatus()+1);
		
		documentrepository.save(mydoc);
		
	}
	//�����ü ���
	public EmployeeEntity getemp(Long hdcode) {
		return employeerepository.findByhdcode(hdcode).get();
	}
	
	//�������� ������ �����߼���,�ϷἭ��,�Ⱒ���� ��������
	public Map<String, Page<DocumentEntity>> godocmanage(){
		
		Map<String, Page<DocumentEntity>> mydoclists =new HashMap<>();
//		List<DocumentEntity> completedoc = documentrepository.findBydocstatus(3,Sort.by(Direction.DESC,"docnum"));
//		
//		List<DocumentEntity> ignoredoc = documentrepository.findBydocstatus(-1,Sort.by(Direction.DESC,"docnum"));
//		
//		List<DocumentEntity> ingdoc = documentrepository.findBydocstatusBetween(0, 2,Sort.by(Direction.DESC,"docnum"));
		
		Page<DocumentEntity> completedoc = documentrepository.findBydocstatus(3, PageRequest.of(0, 5,Sort.by("docnum").descending()));
		
		Page<DocumentEntity> ignoredoc = documentrepository.findBydocstatus(-1,PageRequest.of(0, 5,Sort.by("docnum").descending()));
		
		Page<DocumentEntity> ingdoc = documentrepository.findBydocstatusBetween(0, 2,PageRequest.of(0, 5,Sort.by("docnum").descending()));
		
		mydoclists.put("completedoc", completedoc);
		
		
		mydoclists.put("ignoredoc",ignoredoc);
		
		
		mydoclists.put("ingdoc", ingdoc);
		
		
		System.out.println("completedoc"+completedoc.getContent().size());
		System.out.println("ignoredoc"+ignoredoc.getContent().size());
		System.out.println("ingdoc"+ingdoc.getContent().size());
		
		
		
		return mydoclists;
	}
	//���� ��������
	public DocumentEntity getdoc(Long docnum) {
		Optional<DocumentEntity> mydoc = documentrepository.findById(docnum);
		
		return mydoc.get();
	}
	
	
	//���� ÷������ ó��//�Ⱦ�
	public String attachment(MultipartFile[] formData,Long hdcode) {
		EmployeeEntity employee = getemp(hdcode);
		
		List<DocumentEntity> docs = documentrepository.findAllBydocdrafterOrderByDocnumDesc(employee);
		for(DocumentEntity e : docs) {
			System.out.println("docnum >> "+e.getDocnum());
		}
//		Optional<DocumentEntity> doc = documentrepository.findById(docs.get(0).getDocnum()+1L);
//		System.out.println("att doc num >>>"+doc.get().getDocnum());
		
		JSONObject jsonobject = new JSONObject();
		
		System.out.println("length : "+formData.length);
		for(int i = 0; i<formData.length;i++) {
			
			DocAttachmentEntity attachment = new DocAttachmentEntity();
			
			System.out.println(formData[i].getOriginalFilename());
			String staticpath =applicationyamlread.getPath();
			
			String fileRoot =staticpath+"\\document\\attachment";
			String originalFileName = formData[i].getOriginalFilename();
			String extension =originalFileName.substring(originalFileName.lastIndexOf("."));
			String savedFileName =UUID.randomUUID()+extension;
			File targetFile = new File(fileRoot);
			attachment.setAttname(originalFileName);
			attachment.setAttsavedname(savedFileName);
			
				
				 //�ش� ���丮�� ������� ���丮�� �����մϴ�.
				if (!targetFile.exists()) {
					try{
						targetFile.mkdir(); //���� �����մϴ�.
					    System.out.println("������ �����Ǿ����ϴ�.");
					    System.out.println(targetFile.getPath());
				        } 
				        catch(Exception e){
					    e.getStackTrace();
					}        
			         }else {
					System.out.println("�̹� ������ �����Ǿ� �ֽ��ϴ�.");
				}
				
				
				
				
				File targetFile2 = new File(fileRoot);
				try {
					formData[i].transferTo(new File(targetFile2.getPath()+"\\"+savedFileName));
					jsonobject.put("length", formData[i].getSize());
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
				
			
			
			
			
		}
		
		return jsonobject.toString();
	}
	
	
	
	//https://programmer93.tistory.com/31
		//��
		public String summernoteimgupload(MultipartFile file) {
			JSONObject jsonobject=new JSONObject();
			
			String staticpath=applicationyamlread.getPath();
			
			String fileRoot =staticpath+"\\img\\document";
			String originalFileName = file.getOriginalFilename();
			String extension =originalFileName.substring(originalFileName.lastIndexOf("."));
			String savedFileName =UUID.randomUUID()+extension;
			File targetFile = new File(fileRoot);
			
				
				 //�ش� ���丮�� ������� ���丮�� �����մϴ�.
				if (!targetFile.exists()) {
					try{
						targetFile.mkdir(); //���� �����մϴ�.
					    System.out.println("������ �����Ǿ����ϴ�.");
					    System.out.println(targetFile.getPath());
				        } 
				        catch(Exception e){
					    e.getStackTrace();
					}        
			         }else {
					System.out.println("�̹� ������ �����Ǿ� �ֽ��ϴ�.");
				}
				
				
				
				
				File targetFile2 = new File(fileRoot);
				try {
					file.transferTo(new File(targetFile2.getPath()+"\\"+savedFileName));
					jsonobject.put("url", "/document/"+savedFileName);
					jsonobject.put("responseCode", "success");
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					jsonobject.put("responseCode", "error");
					e.printStackTrace();
				}
				
		
				
				
			System.out.println(jsonobject);
			return jsonobject.toString();
		}


//÷�θ���Ʈ��������
		public List<DocAttachmentEntity> getatt(DocumentEntity doc) {
			List<DocAttachmentEntity> att = docattachmentrepository.findByDocument(doc);
			return att;
		}


//���ϴٿ�
		public void filedown(HttpServletRequest  request,HttpServletResponse response,String fileName,String mynum) throws IOException {
			
			
			ServletContext context = request.getServletContext();
//			String r_path = session.getServletContext().getRealPath("/");
//			System.out.println("Path : "+r_path);
			String staticpath = applicationyamlread.getPath()+"\\document\\"+mynum+"\\attachment\\";
			String filename = fileName;
//			System.out.println("imgPath : "+img_path);
			StringBuffer path = new StringBuffer();
			path.append(staticpath).append(filename);
			
//				File file = new File( path.toString() );
//			    File fileNew = new File( staticpath+"asdf" );
//			    if( file.exists() ) file.renameTo( fileNew );
			
			
			
			//�� ��η� ���ϰ�ü ���� : �߻��ηε� ������ �����ϴ�.
			File downloadFile = new File(path.toString());
			//FileInputStream ��Ʈ������ �о�� : �̹���, ���� �� ���̳ʸ� ������ �о� ���� ���� �ڹ��� ��Ʈ�� ����
			FileInputStream fi = new FileInputStream(downloadFile);
			//��û��ü�κ��� ����� �������� ����Ÿ���� �� : ������ ��Ű��� ���
			String mimeType = context.getMimeType(path.toString());
			//���� ����Ÿ�԰��� ������ �׳� ����Ʈ ��Ʈ������ ����
			if(mimeType ==null) {
				mimeType = "application/octet - stream"; //�������� �ٿ�ε带 �����ϰڴٴ� ����Ÿ���� ����
			}
			//������ ����Ÿ�� ����
			//setContentType("text/html; charset=euc-kr") //html�� �������� ���� �غ�
			response.setContentType(mimeType);
			//�ٿ�ε� �� ������ ���� ����
			response.setContentLength((int) downloadFile.length());
			//�ٿ�ε� Type�� ������
			String headerKey = "Content-Disposition";
			
			String headerValue= String.format("attachment; filename=\"%s\"",downloadFile.getName());
			//String headerValue= String.format("attachment; filename=\"%s\"",filename);
			
			//���� �ٿ�ε� Ÿ���� ������ �ش��� �����ؼ� �������� �����ϸ� �������� �ش� â�� �°� �ٿ�ε� view
			response.setHeader(headerKey, headerValue);
			//�������κ��� ��Ʈ���� ����
			OutputStream outStream = response.getOutputStream();
			//���۸� ������ ������ ���� ����
			byte[] buffer = new byte[BUFFER_SIZE];
			//���� �������� ������, �ڿ� ������ ��
			int bytesRead = -1;
			while((bytesRead = fi.read(buffer))!=-1) {
				outStream.write(buffer, 0, bytesRead);
			}
			fi.close();
			outStream.close();
			
		}


		//��������
		public void deletedoc(String docnum) {
			documentrepository.deleteById(Long.parseLong(docnum));
			
		}


		
		
		
		
		
		//���� ���� �������� 
		public Page<DocumentEntity> getcompltedocpaging(int page, int size) {
			//Page<BoardEntity> pagelist = boardrepository.findAll(PageRequest.of(page, size,Sort.by("bnum").descending()));
			
			Page<DocumentEntity> comdoc_pagelists = documentrepository.findBydocstatus(3, PageRequest.of(page-1, size ,Sort.by("docnum").descending()));
			
			//List<DocumentEntity> domdoc_pagelist =comdoc_pagelists.getContent();
			return comdoc_pagelists;
		}



	
}

