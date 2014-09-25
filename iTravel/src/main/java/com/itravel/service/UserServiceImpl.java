package com.itravel.service;

import java.util.List;

import com.itravel.dao.UserDao;
import com.itravel.dao.UserDaoImpl;
import com.itravel.dto.User;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao = new UserDaoImpl();

	@Override
	public List<User> getUsers() {
		return userDao.getUsers();
	}

}
