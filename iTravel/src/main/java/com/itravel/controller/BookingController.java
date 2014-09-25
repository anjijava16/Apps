package com.itravel.controller;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itravel.dto.Booking;
import com.itravel.dto.User;
import com.itravel.service.BookingService;
import com.itravel.service.BookingServiceImpl;

public class BookingController extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		try{
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			
			String name= request.getParameter("name");
			Integer age = Integer.parseInt(request.getParameter("age"));
			String email = request.getParameter("email");
			Integer mobile = Integer.parseInt(request.getParameter("mobile"));
			String cardType = request.getParameter("cardType");
			Long cardNumber = Long.parseLong(request.getParameter("cardno"));
			Integer expiryMonth = Integer.parseInt(request.getParameter("expiryMonth"));
			Integer expiryYear = Integer.parseInt(request.getParameter("expiryYear"));
			
			User user = new User();
			user.setUserName(name);
			user.setAge(age);
			user.setEmail(email);
			user.setMobile(mobile);
			
			Booking booking = new Booking();
			booking.setCardType(cardType);
			booking.setCardNumber(cardNumber);
			booking.setExpiryMonth(expiryMonth);
			booking.setExpiryYear(expiryYear);
			
			BookingService bookingService = new BookingServiceImpl();
			bookingService.saveBookingDetails(user, booking);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/pages/bookingConfirmed.jsp");
			dispatch.forward(request, response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
