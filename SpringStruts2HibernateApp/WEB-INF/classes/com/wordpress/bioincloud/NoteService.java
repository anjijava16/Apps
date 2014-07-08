package com.wordpress.bioincloud;
import java.util.List;
import com.wordpress.bioincloud.Note;
import com.wordpress.bioincloud.NoteDao;
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
