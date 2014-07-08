package com.wordpress.bioincloud;
import com.wordpress.bioincloud.User;
import java.util.List;
public interface IUserService extends IService{
	void addUser(User user);
	void updateUser(User user);
	List<User> listUser();
	List<User> loginUser(String name, String password);
	List<User> recoverPassword(String name, String email);
	
}
