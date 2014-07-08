package struts2.contactapp.controller;


import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import struts2.contactapp.model.Contact;
import struts2.contactapp.util.HibernateUtil;

public class ContactDao extends HibernateUtil {

	
	
	public Contact add(Contact contact) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(contact);
		session.getTransaction().commit();
		return contact;
	}
	public Contact delete(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Contact contact = (Contact) session.load(Contact.class, id);
		if(null != contact) {
			session.delete(contact);
		}
		session.getTransaction().commit();
		return contact;
	}

	public List<Contact> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Contact> contacts = null;
		try {
			
			contacts = (List<Contact>)session.createQuery("from Contact").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return contacts;
	}
}
