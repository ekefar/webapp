package com.teamdev.webapp1.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
 
	@RequestMapping(value="/Welcome", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
 
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = user.getUsername();
	
		model.addAttribute("login", name);
		model.addAttribute("message", "Spring Security login + database example");
		return "Welcome";
 
	}
 
	@RequestMapping(value="/Login", method = RequestMethod.GET)
	public String login(ModelMap model) {
 
		return "Login";
 
	}
	
	@RequestMapping(value="/Loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 
		model.addAttribute("error", "true");
		return "Login";
 
	}
	
	@RequestMapping(value="/Logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
 
		return "Login";
 
	}
	
}