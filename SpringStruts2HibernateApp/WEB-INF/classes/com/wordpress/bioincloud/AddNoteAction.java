package com.wordpress.bioincloud;
import java.util.Map;

import com.wordpress.bioincloud.Note;
import com.wordpress.bioincloud.INoteService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class AddNoteAction extends ActionSupport{
	private String userId;
	private String title;
	private String content;
	private INoteService noteService;
	
	public String addNote(){
		Map session = ActionContext.getContext().getSession();
		if (!session.containsKey("USER_ID")){
			return "input";
		}
		else{
			Note note = new Note();
			note.setUserId(session.get("USER_ID").toString());
			note.setTitle(title);
			note.setContent(content);
			noteService.addNote(note);
			return "list";		
		}

	}

/*
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
*/	

    
	   public String getUserId(){
	        return userId;
	    }

	    public void setUserId(String userId){
	        this.userId = userId;
	    }
	    public String getTitle(){
	        return title;
	    }

	    public void setTitle(String title){
	        this.title = title;
	    }

	    public String getContent(){
	        return content;
	    }

	    public void setContent(String content){
	        this.content = content;
	    }

	public void setNoteService(INoteService noteService){
		this.noteService = noteService;
	}

}
