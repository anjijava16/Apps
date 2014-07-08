package struts2.spring3.hibernate3.userinfoapp.service;
import java.util.List;

import struts2.spring3.hibernate3.userinfoapp.dto.Note;
import struts2.spring3.hibernate3.userinfoapp.dao.NoteDao;
public class NoteService implements INoteService{
	private NoteDao noteDao;
	public void addNote(Note note){
		noteDao.addNote(note);
	}

	public void updateNote(Note note){
		noteDao.updateNote(note);
	}

	public void deleteNote(Note note){
		noteDao.deleteNote(note);
	}
	
	public List<Note> listNote(int pageNum,int notesPerPage){
		return noteDao.listNote(pageNum,notesPerPage);
	}

	public List<Note> selectNote(String query){
		return noteDao.selectNote(query);
	}
	
	public void setNoteDao(NoteDao noteDao){
		this.noteDao = noteDao;
	}
	
	public NoteDao getNoteDao(){
		return noteDao;
	}

}
