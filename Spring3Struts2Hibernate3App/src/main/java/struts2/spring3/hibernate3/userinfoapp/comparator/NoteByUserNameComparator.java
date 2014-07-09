package struts2.spring3.hibernate3.userinfoapp.comparator;

import struts2.spring3.hibernate3.userinfoapp.dto.Note;
import java.util.Comparator;

public class NoteByUserNameComparator implements Comparator<Note>{
	@Override
    public int compare(Note note1, Note note2) {
        
        return note1.getUserId().compareTo(note2.getUserId());
    }
}
