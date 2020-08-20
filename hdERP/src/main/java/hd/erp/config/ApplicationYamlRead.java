package hd.erp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;



@Configuration
@ConfigurationProperties(prefix = "common")
//@Getter 롬복 ㅠㅠ
//@Setter 롬복 ㅠㅠ
public class ApplicationYamlRead {

	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
}
