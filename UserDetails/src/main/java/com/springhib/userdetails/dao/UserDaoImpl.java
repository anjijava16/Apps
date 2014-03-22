/**
 * 
 */
package com.springhib.userdetails.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springhib.userdetails.dto.User;

/**
 * @author Kondal reddy
 * 22-03-2014
 *
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public List<User> getUsers() {
		@SuppressWarnings("unchecked")
		List<User> userList = sessionFactory.getCurrentSession().createCriteria(User.class).list();
		return userList;
	}

}
