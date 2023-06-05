package uberlite.model;

public class User {
	
	@Override
	public String toString() {
		return "User [name=" + name + ", phoneNo=" + phoneNo + ", email=" + email + ", password=" + password + "]";
	}
	private String name;
    private String phoneNo;
    private String email;
    private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}