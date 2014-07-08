package struts2.spring3.hibernate3.userinfoapp.action;
import java.util.Map;
import java.util.List;

import struts2.spring3.hibernate3.userinfoapp.service.INoteService;
import struts2.spring3.hibernate3.userinfoapp.dto.Note;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class DeleteNoteAction extends ActionSupport{
	private String noteId;
	private INoteService noteService;
	
	public String deleteNote(){
		List<Note> notes = noteService.selectNote(noteId);
		for (Note temp : notes) {
			noteService.deleteNote(temp);
		}
		
		return SUCCESS;		
	}

	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	
	public void setNoteService(INoteService noteService){
		this.noteService = noteService;
	}

}
