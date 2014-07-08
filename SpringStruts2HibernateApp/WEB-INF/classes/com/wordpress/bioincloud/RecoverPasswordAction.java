package com.wordpress.bioincloud;

import com.wordpress.bioincloud.IUserService;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
public class RecoverPasswordAction extends ActionSupport{
	private String name;
	private String email;
	private IUserService userService;
	private JavaMailSender mailSender;
	
	private List<User> users;

	public String recoverPassword(){
		
		//users = userService.recoverPassword(name, email);
		if (users.size() > 0){
			SimpleMailMessage mail = new SimpleMailMessage();  
			mail.setFrom(email);  
			mail.setTo(getEmail());
			mail.setSubject("Your password");  
			//mail.setText("hello");
			mail.setText("Your password is: "+users.get(0).getPassword());  
			mailSender.send(mail); 
			return "success";
		}else{
			return "error";
		}
	}

   
	public void validate(){ 
		if (getName().length() == 0){
			this.addFieldError("name", "User name is required"); 
		}
		if (getEmail().length() == 0){
			this.addFieldError("email", "Email is required"); 
		}

		else {
			users = userService.recoverPassword(name, email);
			if(users.size() == 0){
				this.addActionError("Invalid user name or email, try it again.");
				//System.out.println("************************");
			}
		}
	}
	

    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setUserService(IUserService userService){
		this.userService = userService;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

}
