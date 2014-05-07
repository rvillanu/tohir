package tohir.dto;

public class User {
	private String name;
	private String username;
	private String email;
	
	public User(String username) {
		this.username = username;
		String name;
		String email;
	}
	
	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}


}
