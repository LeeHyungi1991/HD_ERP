package hd.erp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import hd.erp.config.ApplicationYamlRead;
import net.minidev.json.JSONArray;

@SpringBootTest
public class Hderptestjson {
	//@Test
	public void asdf() throws JSONException {
		 JSONObject jsonObject1 = new JSONObject(); // 중괄호에 들어갈 속성 정의 { "a" : "1", "b" : "2" }
	        JSONArray jsonArray1 = new JSONArray(); // 대괄호 정의 [{ "a" : "1", "b" : "2" }]
	        JSONObject finalJsonObject1 = new JSONObject(); // 중괄호로 감싸 대괄호의 이름을 정의함 { "c" : [{  "a" : "1", "b" : "2" }] }
	 
	        jsonObject1.put("이름", "잠자리");
	        jsonObject1.put("다리갯수", "6");
	        jsonArray1.add(jsonObject1);
	 
	        jsonObject1 = new JSONObject();
	        jsonObject1.put("이름", "사슴벌레");
	        jsonObject1.put("다리갯수", "6");
	        jsonArray1.add(jsonObject1);
	        
	        finalJsonObject1.put("곤충", jsonArray1);
	 
	        finalJsonObject1.put("동물", "코끼리");
	        finalJsonObject1.put("식물", "무궁화");
	        
	        System.out.println(finalJsonObject1);
	}
	
	@Autowired
	ApplicationYamlRead applicationyamlread;
	
	@Test
	public void aa() throws JSONException {
		String path = applicationyamlread.getPath();
		System.out.println(path);
		JSONObject jsonobject1 = new JSONObject();
		JSONArray jsonarr1 = new JSONArray();
		JSONObject finalobject = new JSONObject();
		
		jsonobject1.put("이름1","임성윤");
		jsonobject1.put("이름2", "최호연");
		jsonobject1.put("이름3", "이현기");
		jsonobject1.put("이름4", "조승열");
		
		jsonarr1.add(jsonobject1);
		finalobject.put("D조", jsonarr1);
		
		JSONObject jsonobject2 = new JSONObject();
		jsonarr1 = new JSONArray();
		jsonobject2.put("이름1", "박현두");
		jsonobject2.put("이름2", "mj");
		jsonarr1.add(jsonobject2);		
		finalobject.put("C조", jsonarr1);
		
		jsonobject1 = new JSONObject();
		jsonobject1.put("kosmo", finalobject);
		
		System.out.println(jsonarr1);
		System.out.println(jsonobject1);
		System.out.println(finalobject);
		
		
		
	}
	
	public void asdfAsdf() {
		//ArrayList<String> asdf = new ArrayList<String>();
		
		//asdf.add("1");
		Set<Integer>asdf =new HashSet<>();
		asdf.add(5);
		asdf.add(3);
		ArrayList<Integer> ffff = new ArrayList<Integer>();
		
		
		
	}

}
