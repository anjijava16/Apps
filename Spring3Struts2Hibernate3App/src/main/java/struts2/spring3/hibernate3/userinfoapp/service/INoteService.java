package struts2.spring3.hibernate3.userinfoapp.service;
import java.util.List;

import struts2.spring3.hibernate3.userinfoapp.dto.Note;
import struts2.spring3.hibernate3.userinfoapp.dto.User;

public interface INoteService extends IService{
	void addNote(Note note);
	void updateNote(Note note);
	void deleteNote(Note note);
	List<Note> listNote(int pageNum,int notesPerPage);
	List<Note> selectNote(String query);
}