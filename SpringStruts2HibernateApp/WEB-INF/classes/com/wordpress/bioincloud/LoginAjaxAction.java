package com.wordpress.bioincloud;

import com.wordpress.bioincloud.IUserService;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class LoginAjaxAction extends ActionSupport{
	private String name;
	private String password;
	private String login_status;
	private IUserService userService;

	
	public String loginAjaxUser(){
        ActionContext ctx = ActionContext.getContext();  
        Map session = ctx.getSession();  
        session.put("USER_ID",getName());
		if (userService.loginUser(name, password).size() > 0){
			login_status = name;
		}else{
			login_status = "login failed, please try it again.";
		}
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

		else if(userService.loginUser(name, password).size() == 0){
			//System.out.println("************************");
			this.addActionError("Wrong user name or password, try it again.");
			//System.out.println("************************");
		}
	}
	**/
	
	//pay attention to the method name, cannot use getLoginStatus or others
	//must use getLogin_status since the variable name is login_status
	public String getLogin_status() {
		return login_status;
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

	//must comment this method out, or returned json error 
	//public IUserService getUserService(){
		//return userService;
	//}	
	
	public void setUserService(IUserService userService){
		this.userService = userService;
	}

}
