package struts2.spring3.hibernate3.userinfoapp.comparator;
import java.util.Comparator;

import struts2.spring3.hibernate3.userinfoapp.dto.Note;

public class NoteByTitleComparator implements Comparator<Note> {
	@Override
    public int compare(Note note1, Note note2) {
        
        return note1.getTitle().compareTo(note2.getTitle());
    }
}
