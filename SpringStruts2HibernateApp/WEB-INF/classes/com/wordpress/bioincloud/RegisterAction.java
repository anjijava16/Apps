package com.wordpress.bioincloud;
import com.wordpress.bioincloud.User;
import com.wordpress.bioincloud.IUserService;
import com.opensymphony.xwork2.ActionSupport;
public class RegisterAction extends ActionSupport{
	private String name;
	private String password;
	private String email;
	private String gender;
	private IUserService userService;
	
	public String registerUser(){
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		user.setGender(gender);
		userService.addUser(user);
		this.setName(null);//not fill the next form automatically
		setPassword(null);//not fill the next form automatically, use "this" or not
		return "login";		
	}

	public void validate(){
		//if (getName() == null) { //cannot use this???
			//addFieldError("name", "User name is required"); 
			//addActionError("User name is required");
		//}
		if (getName().length() == 0){
			this.addFieldError("name", "User name is required"); 
			//addActionError("User name is required");
		}
		if (getPassword().length() == 0){
			this.addFieldError("password", "Password is required"); 
		}
		if (getEmail().length() == 0){
			this.addFieldError("email", "Email is required"); 
		}
		//else if(userService.loginUser(name, password).size() == 0){
			//addActionError("Wrong user name or password, try it again.");
		//}
	}
	

    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setUserService(IUserService userService){
		this.userService = userService;
	}

}
