package struts2.spring3.hibernate3.userinfoapp.dao;
import java.util.List;

import struts2.spring3.hibernate3.userinfoapp.dto.User;
public interface IDao {
		void addUser(User user);
		void updateUser(User user);
		List<User> listUser();
		List<User> loginUser(String name, String password);
}

