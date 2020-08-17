package hd.erp.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
		
		
		
		registry.addResourceHandler("/board/**")
		.addResourceLocations("file:///"+staticpath+"/img/board/");
		registry.addResourceHandler("/document/**")
		.addResourceLocations("file:///"+staticpath+"/img/document/");
//.addResourceLocations("file:///C:/ikosmo64/spring/realerp/hdERP/src/main/resources/static/img/board/");
				
	}
	

	

}
