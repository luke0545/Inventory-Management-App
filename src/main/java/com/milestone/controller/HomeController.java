package com.milestone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home Controller
 * @author Michal Uchmanowicz
 *
 */
@Controller
@RequestMapping("/")
public class HomeController 
{
	/**
	 * displays a view 
	 * @param model - model passed in
	 * @return returns index view based on model
	 */
	@GetMapping("/")
	public String display(Model model)
	{
		return "index";
	}
}
