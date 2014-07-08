package com.wordpress.bioincloud;
import com.wordpress.bioincloud.User;
import java.util.List;
public interface IDao {
		void addUser(User user);
		void updateUser(User user);
		List<User> listUser();
		List<User> loginUser(String name, String password);
}

