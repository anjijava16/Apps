/**
 * 
 */
package com.springhib.userdetails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springhib.userdetails.dao.UserDao;
import com.springhib.userdetails.dto.User;

/**
 * @author KR
 * 22-3-14
 *
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	/* (non-Javadoc)
	 * @see com.springhib.userdetails.service.UserService#addUser(com.springhib.userdetails.dto.User)
	 */
	@Override
	public void addUser(User user) {
		userDao.saveUser(user);

	}

	/* (non-Javadoc)
	 * @see com.springhib.userdetails.service.UserService#getUsers()
	 */
	@Override
	public List<User> getUsers() {
		List<User> userLilst = userDao.getUsers();
		return userLilst;
	}

}
