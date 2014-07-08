package com.wordpress.bioincloud;

public class Note implements java.io.Serializable{
	private int noteId;
	private String userId;
	private String title;
    private String content;


    public Note() {
    }

    public int getNoteId(){
        return noteId;
    }
    public void setNoteId(int noteId){
        this.noteId = noteId;
    }
    
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }
}