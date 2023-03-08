package com.milestone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Login controller is used to log the user in to view their inventory, uses Spring Security for Authentication
 */
@Controller
@RequestMapping("/login")
public class LoginController 
{
	/**
	 * return login page after successful login through spring security
	 * @param model - loginModel
	 * @return "login" page
	 */
	@GetMapping("/")
	public String display(Model model)
	{
		return "login";
	}
}

