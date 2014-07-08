package com.wordpress.bioincloud;
import java.util.List;
import com.wordpress.bioincloud.User;
import com.wordpress.bioincloud.UserDao;
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
