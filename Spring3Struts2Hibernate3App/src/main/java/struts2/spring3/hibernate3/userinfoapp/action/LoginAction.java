package struts2.spring3.hibernate3.userinfoapp.action;

import java.util.Map;

import struts2.spring3.hibernate3.userinfoapp.service.IUserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class LoginAction extends ActionSupport{
	private String name;
	private String password;
	private IUserService userService;

	
	public String loginUser(){
        ActionContext ctx = ActionContext.getContext();  
        Map session = ctx.getSession();  
        session.put("USER_ID",getName());
		if (userService.loginUser(name, password).size() > 0){
			return "success";
		}else{
			return "error";
		}
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

		else if(userService.loginUser(name, password).size() == 0){
			//System.out.println("************************");
			this.addActionError("Wrong user name or password, try it again.");
			//System.out.println("************************");
		}
	}
	

    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}

	public IUserService getUserService(){
		return userService;
	}	
	
	public void setUserService(IUserService userService){
		this.userService = userService;
	}

}
