package struts2.contactapp.action;

import java.util.List;

import struts2.contactapp.controller.ContactDao;
import struts2.contactapp.model.Contact;

import com.opensymphony.xwork2.ActionSupport;


public class ContactAction extends ActionSupport {

	private static final long serialVersionUID = 9149826260758390091L;
	private Contact contact;
	private List<Contact> contactList;
	private Long id;

	
	private ContactDao contactDao;

	public ContactAction() {
		contactDao = new ContactDao();
	}

	public String execute() {
		this.contactList = contactDao.list();
		return SUCCESS;
	}

	public String add() {
		System.out.println(getContact());
		try {
			contactDao.add(getContact());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.contactList = contactDao.list();
		return SUCCESS;
	}

	public String delete() {
		contactDao.delete(getId());
		return SUCCESS;
	}

	public Contact getContact() {
		return contact;
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public void setContactList(List<Contact> contactsList) {
		this.contactList = contactsList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
