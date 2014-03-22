/**
 * 
 */
package com.springhib.userdetails.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springhib.userdetails.dto.User;
import com.springhib.userdetails.service.UserService;

/**
 * @author Kondal Reddy
 * 22-03-2014
 *
 */
@Controller
public class HomeController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/register")
	public ModelAndView loadRegistrationForm(@ModelAttribute("user")User user, BindingResult result){
		List<String> genderList = new ArrayList<String>();
		genderList.add("Male");
		genderList.add("Female");
		
		List<String> cityList = new ArrayList<String>();
		cityList.add("Hyderabad");
		cityList.add("Delhi");
		cityList.add("Kolkata");
		cityList.add("Bangalore");
		cityList.add("Chennai");
		cityList.add("Mumbai");
		
		Map<String,Object> model = new HashMap<String, Object>();
		model.put("gender", genderList);
		model.put("city", cityList);
		
		return new ModelAndView("Register", "model", model);
		
	}
	
	@RequestMapping("/saveUser")
	public ModelAndView saveUserData(@ModelAttribute("user")User user, BindingResult result){
		userService.addUser(user);
		return new ModelAndView("redirect:/userList.html");
		
	}
	
	@RequestMapping("/userList")
	public ModelAndView loadUsers(@ModelAttribute("user")User user, BindingResult result){
		/*Map<String,Object> userMap = (Map<String, Object>) userService.getUsers();
		return new ModelAndView("UserDetails", userMap);*/
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", userService.getUsers());
		return new ModelAndView("UserDetails", model);
		
	}
}
