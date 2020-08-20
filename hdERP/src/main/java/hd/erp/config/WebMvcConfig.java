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

	
	
	//web root가 아닌 외부 경로에 있는 리소스를 url로 불러올 수 있도록 설정
    //현재 localhost:8090/summernoteImage/1234.jpg
    //로 접속하면 C:/summernote_image/1234.jpg 파일을 불러온다.
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
