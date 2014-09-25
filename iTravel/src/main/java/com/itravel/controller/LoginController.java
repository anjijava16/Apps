package com.itravel.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itravel.dto.Search;
import com.itravel.dto.User;
import com.itravel.service.SearchService;
import com.itravel.service.SearchServiceImpl;
import com.itravel.service.UserService;
import com.itravel.service.UserServiceImpl;

public class LoginController extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		try{
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			
			UserService userService = new UserServiceImpl();
			List<User> userList = userService.getUsers();
			
			session.setAttribute("user", userList);
			
			for (int i = 0; i < userList.size(); i++) {
		        if (userList.get(i).getUserName().equals(userName) && userList.get(i).getPassword().equals(password)) {
		        	RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/pages/paymentDetails.jsp");
					dispatch.forward(request, response);
		        }
		        else{
		        	System.out.println("invalid details");
		        }
		    }
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
