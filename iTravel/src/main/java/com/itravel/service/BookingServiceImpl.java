package com.itravel.service;

import com.itravel.dao.BookingDao;
import com.itravel.dao.BookingDaoImpl;
import com.itravel.dto.Booking;
import com.itravel.dto.User;

public class BookingServiceImpl implements BookingService {
	
	private BookingDao bookingDao = new BookingDaoImpl();
	
	public void saveBookingDetails(User user,Booking booking){
		bookingDao.saveBookingDetails(user, booking);
	}
}
