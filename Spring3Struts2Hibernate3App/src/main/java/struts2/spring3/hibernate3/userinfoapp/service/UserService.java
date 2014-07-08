package struts2.spring3.hibernate3.userinfoapp.service;
import java.util.List;

import struts2.spring3.hibernate3.userinfoapp.dto.User;
import struts2.spring3.hibernate3.userinfoapp.dao.UserDao;
public class UserService implements IUserService{
	private UserDao userDao;
	public void addUser(User user){
		userDao.addUser(user);
	}

	public void updateUser(User user){
		userDao.updateUser(user);
	}
	public List<User> listUser(){
		return userDao.listUser();
	}
	public List<User> loginUser(String name, String password){
		return userDao.loginUser(name, password);
		
	}
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
	
	public UserDao getUserDao(){
		return userDao;
	}
	
	public List<User> recoverPassword(String name, String email){
		return userDao.recoverPassword(name,email);
	}
	
}
