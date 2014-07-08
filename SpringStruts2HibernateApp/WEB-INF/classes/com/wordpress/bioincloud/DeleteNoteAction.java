package com.wordpress.bioincloud;
import java.util.Map;

import com.wordpress.bioincloud.Note;
import java.util.List;
import com.wordpress.bioincloud.INoteService;
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
