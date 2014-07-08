package struts2.spring3.hibernate3.userinfoapp.dao;
import java.util.List;

import struts2.spring3.hibernate3.userinfoapp.dto.Note;
public interface INoteDao {
	void addNote(Note note);
	void updateNote(Note note);
	void deleteNote(Note note);
	List<Note> listNote(int pageNum,int notesPerPage);
}
