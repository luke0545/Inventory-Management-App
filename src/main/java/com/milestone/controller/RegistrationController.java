package com.milestone.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.milestone.business.RegistrationBusinessServiceInterface;
import com.milestone.model.LoginModel;
import com.milestone.model.UserModel;

/**
 * Register controller to register user
 * @author Luke Walder
 */
@Controller
@RequestMapping("/register")
public class RegistrationController 
{
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
	@Autowired
	private RegistrationBusinessServiceInterface regService;

    /**
     * displays registration page
 	* @param model displays registration page
	* @return String "register" 
 	*/
    @GetMapping("/")
    public String display(Model model)
    {
	
        //Display Register form view
    	model.addAttribute("UserInUse", false);
        model.addAttribute("userModel", new UserModel());
        model.addAttribute("loginModel", new LoginModel());
        return "register";
    }
	/**
     * displays success or error message upon completing registration
	 * @param userModel - user from form
	 * @param loginModel - login from form
	 * @param bindingResult - binding result to check for validaiton errors
	 * @param model - model to attach attributes to.
	 * @return String - view "products" to be returned
	 */
    @PostMapping("/doRegister")
    public String doRegister(@Valid UserModel userModel, BindingResult bindingResult, LoginModel loginModel, Model model)
    {
    	model.addAttribute("userModel", userModel);
        // Check for validation errors
        if (bindingResult.hasErrors()) 
        {
            return "register";
        }
//encode password
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        
//if User exists return error and return register page
        if (regService.checkUserExists(userModel))
        {
          	model.addAttribute("UserCreationResponse", "User Exists, unable to create User and roles");
          	return "register";
        }
//if add user fails
        if (!regService.addUser(userModel))
        {
        	model.addAttribute("UserCreationResponse", "Failure to Create User");
        	return "registerFailed";
        }
//if add user roles fails
        if (!regService.addUserRoles(userModel))
        {
        	model.addAttribute("UserCreationResponse", "Failure to Create User Roles");
        	return "registerFailed";
        }
//passed all fails, user and roles added
        model.addAttribute("UserCreationResponse", "Created User and roles");
    	return "registerSuccess";
       
    }	
}