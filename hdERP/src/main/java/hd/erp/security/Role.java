package hd.erp.security;


//@AllArgsConstructor 롬복 ㅠㅠ
//@Getter 롬복 ㅜ
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
