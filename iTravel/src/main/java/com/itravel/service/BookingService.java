package com.itravel.service;

import com.itravel.dto.Booking;
import com.itravel.dto.User;

public interface BookingService {
	public void saveBookingDetails(User user,Booking booking);
}
