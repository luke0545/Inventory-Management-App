package com.milestone.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global Exception Handler
 * 
 * @author markreha
 *
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler
{
	/**
	 * Controller method to handle the unhandled Exception.
	 * 
	 * @param ex - The source Exception
	 * @param model - model
	 * @return model - model and name of the Exception View
	 */
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex, Model model)
	{
		// Create a Model and View, populate with the Exception information, and display a common Error Page
		model.addAttribute("error", ex.getMessage());
		return "exception";
	}
}
