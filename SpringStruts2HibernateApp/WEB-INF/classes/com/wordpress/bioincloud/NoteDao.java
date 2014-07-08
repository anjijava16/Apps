package com.wordpress.bioincloud;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.wordpress.bioincloud.INoteDao;
import com.wordpress.bioincloud.Note;
public class NoteDao extends HibernateDaoSupport implements INoteDao{

	public void addNote(Note note){
		getHibernateTemplate().save(note);
	}
	public void updateNote(Note note){
		getHibernateTemplate().saveOrUpdate(note);
	}

	public List<Note> listNote(int pageNum,int notesPerPage){
		Session session = SessionFactoryUtils.getSession(getSessionFactory(), true);
		Query query = session.createQuery("from Note");
		query.setFirstResult(notesPerPage * pageNum);//0 based
		query.setMaxResults(notesPerPage);
		List notes = query.list(); 

		//List<Note> notes = (List<Note>) getHibernateTemplate().find("from Note");//from Note, not from noteInfo(table name)
		return notes;
	} 
	public void deleteNote(Note note){
		getHibernateTemplate().delete(note);
	}
	
	public List<Note> selectNote(String query){
		List<Note> notes = (List<Note>) getHibernateTemplate().find("from Note n where n.noteId='"+query+"'");
		return notes;
	}	
}

