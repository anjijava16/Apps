package com.itravel.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.itravel.dto.Search;

@SuppressWarnings("unchecked")
public class SearchDaoImpl implements SearchDao {

	@Override
	public List<Search> getBuses(Integer searchId) {
		SessionFactory sessionFactory = new AnnotationConfiguration().
                configure().
                //addPackage("com.xyz") //add package if used.
                addAnnotatedClass(com.itravel.dto.Search.class).
                addAnnotatedClass(com.itravel.dto.User.class).
                buildSessionFactory();
		//Configuration cfg = new Configuration();
		//cfg.configure("hibernate.cfg.xml");
		//SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//List<Search> searchList = session.createQuery("FROM Search AS s LEFT JOIN User AS u ON s.id = :u.userId").list();
		return session.getNamedQuery("findBusInfoList").list();
		//return searchList;
	}

}
