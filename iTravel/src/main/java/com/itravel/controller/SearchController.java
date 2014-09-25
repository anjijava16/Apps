package com.itravel.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itravel.dto.Search;
import com.itravel.service.SearchService;
import com.itravel.service.SearchServiceImpl;

public class SearchController extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		try{
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			ServletContext application = getServletContext();
			
			Integer searchId = Integer.parseInt(request.getParameter("searchId"));
			String from = request.getParameter("from");
			String to = request.getParameter("to");
			String doj = request.getParameter("doj");
			String dor = request.getParameter("dor");
			
			SearchService searchService = new SearchServiceImpl();
			
			List<Search> searchResult = new ArrayList<Search>();
			searchResult = searchService.getBuses(searchId);
			
			System.out.println("searchlist="+searchResult);
			application.setAttribute("searchResult", searchResult);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/pages/searchResult.jsp");
			dispatch.forward(request, response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		
	}
}
