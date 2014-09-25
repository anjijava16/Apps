package com.itravel.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import util.HibernateUtil;

import com.itravel.dto.Booking;
import com.itravel.dto.User;

public class BookingDaoImpl implements BookingDao {

	@Override
	public void saveBookingDetails(User user, Booking booking) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		/*Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        Session session = sessionFactory.getCurrentSession();*/
		session.beginTransaction();
		
		session.save(user);
		session.save(booking);

		session.getTransaction().commit();
		//session.close();
		
	}

}
