package struts2.spring3.hibernate3.userinfoapp.action;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import struts2.spring3.hibernate3.userinfoapp.service.INoteService;
import struts2.spring3.hibernate3.userinfoapp.dto.Note;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class ListNoteAction extends ActionSupport{
	private String userId;
	private String title;
	private String content;
	private INoteService noteService;
	private List<Note> noteList;
	private int pageNum = 0;
	private int notesPerPage =10;
	private int totalPages;
	public String listNote() {
		Map session = ActionContext.getContext().getSession();
		if (!session.containsKey("USER_ID")){
			return "input";
		}
		else{
			noteList = noteService.listNote(pageNum,notesPerPage);
			return "list";
		}
	}
  
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}

	public INoteService getNoteService(){
		return noteService;
	}	
	public void setNoteService(INoteService noteService){
		this.noteService = noteService;
	}

	public List<Note> getNoteList() {
		return noteList;
	}
	public void setNoteList(List<Note> noteList) {
		this.noteList = noteList;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public int getNotesPerPage() {
		return notesPerPage;
	}
	public void setNotesPerPage(int notesPerPage) {
		this.notesPerPage = notesPerPage;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
}
