/**
 * 
 */
package com.springhib.userdetails.service;

import java.util.List;

import com.springhib.userdetails.dto.User;

/**
 * @author KR
 * 22-3-14
 *
 */
public interface UserService {
	public void addUser(User user);
	public List<User> getUsers();
}
