package hd.erp.security;


//@AllArgsConstructor �Һ� �Ф�
//@Getter �Һ� ��
public enum Role {

	ADMIN("ROLE_ADMIN"),
	MEMBER("ROLE_MEMBER"),
	USER("ROLE_USER");
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private Role(String value) {
		this.value = value;
	}
	
	
	
}
