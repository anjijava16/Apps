package com.itravel.dto;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name="userId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="age")
	private Integer age;
	
	@Column(name="email")
	private String email;
	
	@Column(name="mobile")
	private Integer mobile;
	
	/* @OneToOne
	 @PrimaryKeyJoinColumn
	 private Search search;*/
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getMobile() {
		return mobile;
	}
	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}
	/*public Search getSearch() {
		return search;
	}
	public void setSearch(Search search) {
		this.search = search;
	}*/
	
	
}
