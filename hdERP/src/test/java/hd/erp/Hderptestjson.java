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
		 JSONObject jsonObject1 = new JSONObject(); // �߰�ȣ�� �� �Ӽ� ���� { "a" : "1", "b" : "2" }
	        JSONArray jsonArray1 = new JSONArray(); // ���ȣ ���� [{ "a" : "1", "b" : "2" }]
	        JSONObject finalJsonObject1 = new JSONObject(); // �߰�ȣ�� ���� ���ȣ�� �̸��� ������ { "c" : [{  "a" : "1", "b" : "2" }] }
	 
	        jsonObject1.put("�̸�", "���ڸ�");
	        jsonObject1.put("�ٸ�����", "6");
	        jsonArray1.add(jsonObject1);
	 
	        jsonObject1 = new JSONObject();
	        jsonObject1.put("�̸�", "�罿����");
	        jsonObject1.put("�ٸ�����", "6");
	        jsonArray1.add(jsonObject1);
	        
	        finalJsonObject1.put("����", jsonArray1);
	 
	        finalJsonObject1.put("����", "�ڳ���");
	        finalJsonObject1.put("�Ĺ�", "����ȭ");
	        
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
		
		jsonobject1.put("�̸�1","�Ӽ���");
		jsonobject1.put("�̸�2", "��ȣ��");
		jsonobject1.put("�̸�3", "������");
		jsonobject1.put("�̸�4", "���¿�");
		
		jsonarr1.add(jsonobject1);
		finalobject.put("D��", jsonarr1);
		
		JSONObject jsonobject2 = new JSONObject();
		jsonarr1 = new JSONArray();
		jsonobject2.put("�̸�1", "������");
		jsonobject2.put("�̸�2", "mj");
		jsonarr1.add(jsonobject2);		
		finalobject.put("C��", jsonarr1);
		
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
