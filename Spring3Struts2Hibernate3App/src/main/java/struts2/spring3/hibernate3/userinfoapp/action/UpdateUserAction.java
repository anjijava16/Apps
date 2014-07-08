package struts2.spring3.hibernate3.userinfoapp.action;
import struts2.spring3.hibernate3.userinfoapp.service.IUserService;
import struts2.spring3.hibernate3.userinfoapp.dto.User;

import com.opensymphony.xwork2.ActionSupport;
public class UpdateUserAction extends ActionSupport{
	private String name;
	private IUserService userService;
	
	public String updateUser(){
		User user = new User();
		user.setName(name);
		userService.updateUser(user);
		return SUCCESS;		
	}

	/**
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
	**/
	

    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserService(IUserService userService){
		this.userService = userService;
	}

}
