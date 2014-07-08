package com.wordpress.bioincloud;
import java.util.List;

import com.wordpress.bioincloud.Note;
public interface INoteDao {
	void addNote(Note note);
	void updateNote(Note note);
	void deleteNote(Note note);
	List<Note> listNote(int pageNum,int notesPerPage);
}
