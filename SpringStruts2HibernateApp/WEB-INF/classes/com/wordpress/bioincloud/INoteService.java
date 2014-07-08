package com.wordpress.bioincloud;
import com.wordpress.bioincloud.User;
import java.util.List;
public interface INoteService extends IService{
	void addNote(Note note);
	void updateNote(Note note);
	void deleteNote(Note note);
	List<Note> listNote(int pageNum,int notesPerPage);
	List<Note> selectNote(String query);
}