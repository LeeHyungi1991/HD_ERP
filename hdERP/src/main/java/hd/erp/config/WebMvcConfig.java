package hd.erp.config;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Component
public class WebMvcConfig implements WebMvcConfigurer{
	 
	@Autowired
	ApplicationYamlRead applicationyamlread;

	
	
	//web root�� �ƴ� �ܺ� ��ο� �ִ� ���ҽ��� url�� �ҷ��� �� �ֵ��� ����
    //���� localhost:8090/summernoteImage/1234.jpg
    //�� �����ϸ� C:/summernote_image/1234.jpg ������ �ҷ��´�.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		String staticpath = applicationyamlread.getPath();
		////Object principals = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//Principal principal =(Principal) principals;
		//System.out.println(principal.getName());
		registry.addResourceHandler("/board/**")
		.addResourceLocations("file:///"+staticpath+"/img/board/");
		registry.addResourceHandler("/document/**")
		.addResourceLocations("file:///"+staticpath+"/img/document/");
		registry.addResourceHandler("/img/**")
		.addResourceLocations("file:///"+staticpath+"/img/");
		
//.addResourceLocations("file:///C:/ikosmo64/spring/realerp/hdERP/src/main/resources/static/img/board/");
				
	}
	

	

}
