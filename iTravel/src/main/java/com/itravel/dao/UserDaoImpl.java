package com.itravel.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.itravel.dto.Search;
import com.itravel.dto.User;

public class UserDaoImpl implements UserDao {

	@Override
	public List<User> getUsers() {
		SessionFactory sessionFactory = new AnnotationConfiguration().
                configure().
                addAnnotatedClass(com.itravel.dto.User.class).
                buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		return session.createQuery("from User").list();
	}

}
