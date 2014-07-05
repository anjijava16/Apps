/**
 * 
 */
package studentapp.form;

import javax.persistence.*;

/**
 * @author Home
 *
 */
@Entity
@Table(name="student")
public class Student {
	
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	//private Course course;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the course
	 */
/*	public Course getCourse() {
		return course;
	}
	*//**
	 * @param course the course to set
	 *//*
	public void setCourse(Course course) {
		this.course = course;
	}
	*/
	
}
