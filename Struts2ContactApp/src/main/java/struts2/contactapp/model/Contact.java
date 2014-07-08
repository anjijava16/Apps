package struts2.contactapp.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="contacts")
public class Contact implements Serializable{
	
	private static final long serialVersionUID = -8767337896773261247L;

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private Date created;

	@Id
	@GeneratedValue
	@Column(name="id")
	public Long getId() {
		return id;
	}
	@Column(name="firstname")
	public String getFirstName() {
		return firstName;
	}
	@Column(name="lastname")
	public String getLastName() {
		return lastName;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	@Column(name="telephone")
	public String getTelephone() {
		return telephone;
	}
	
	@Column(name="created")
	public Date getCreated() {
		return created;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
}

