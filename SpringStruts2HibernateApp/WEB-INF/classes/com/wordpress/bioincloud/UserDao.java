package com.wordpress.bioincloud;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.wordpress.bioincloud.IDao;
import com.wordpress.bioincloud.User;
public class UserDao extends HibernateDaoSupport implements IDao{

	//add the customer
	public void addUser(User user){
		getHibernateTemplate().save(user);
	}
	public void updateUser(User user){
		getHibernateTemplate().saveOrUpdate(user);
	}
	public void registerUser(User user){
		getHibernateTemplate().save(user);
	}
	public List<User> listUser(){
		List<User> users = (List<User>) getHibernateTemplate().find("from User");
		return users;
	} 
	public List<User> loginUser(String name, String password){
		return getHibernateTemplate().find("from User u where u.name='"+name+"' and u.password='"+password+"'");
	}
	
	public List<User> recoverPassword(String name, String email){
		return getHibernateTemplate().find("from User u where u.name='"+name+"' and u.email='"+email+"'");
	}
	//return all the users in list
	//public List<User> listUser(){
		//return getHibernateTemplate().find("from User");		
	//}
 
}

