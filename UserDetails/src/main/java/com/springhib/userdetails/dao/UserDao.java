/**
 * 
 */
package com.springhib.userdetails.dao;

import java.util.List;

import com.springhib.userdetails.dto.User;

/**
 * @author Kondal Reddy
 * 22-03-2014
 *
 */
public interface UserDao {
	public void saveUser(User user);
	public List<User> getUsers();
}
