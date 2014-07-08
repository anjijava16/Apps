package com.wordpress.bioincloud;
import com.wordpress.bioincloud.User;
import java.util.ArrayList;
import java.util.List;
import com.wordpress.bioincloud.IUserService;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class UserAction extends ActionSupport{
	private String name;
	private String password;
	private String email;
	private String gender;
	private IUserService userService;
	private List<User> userList;

	public String listUser() {
		Map session = ActionContext.getContext().getSession();
		if (!session.containsKey("USER_ID")){
			return "input";
		}
		else{
			userList = userService.listUser();
			return "success";
		}
	}
	//public String deleteUser() {
		//setUsername("zcai");
		//if (this.username.equals("admin")
				//&& this.password.equals("admin123")) {
			//return "success";
		//} else {
		//	return "error";
		//}
	//}

    
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
	public IUserService getUserService(){
		return userService;
	}	
	public void setUserService(IUserService userService){
		this.userService = userService;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
