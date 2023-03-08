package com.milestone.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.milestone.business.FriendsBusinessServiceInterface;
import com.milestone.model.FriendModel;


/**
 * friends controller used view, add, edit, and delete friends.
 */
@Controller
@RequestMapping("/friends")
public class FriendsController 
{
	/**
	 * autowired business service.
	 */
	@Autowired
	private FriendsBusinessServiceInterface friendService;
	
	/**
	 * display, grabs all friends and requests of currently logged in user
	 * @param model - model to attach attributes to.
	 * @param principal - Security User Object provided by Spring Security
	 * @return "friends" page.
	 */
	@GetMapping("/")
	public String display(Model model, Principal principal)
	{
		//obtain username
		String name = principal.getName();
		//obtain requests and friends per user
		List<FriendModel> friendsList = friendService.getFriendsByUsername(name);
		
		//send data to template
		model.addAttribute("friends", friendsList);
		model.addAttribute("username", name);

		return "friends";
	}
	
	/**
	 * addFriend, takes username string from textbox and attempts to send that user a request 
	 * @param friendUsername - friend's username
	 * @param model - model to attach attributes to.
	 * @param principal - Security User Object provided by Spring Security
	 * @return display()- friends page with updated details
	 */
	@PostMapping("/addFriend")
	public String addFriend(@RequestParam(name="friendUsername") String friendUsername, Model model, Principal principal)
	{
		String userUsername = principal.getName();
		//attempt to add a friend and return results with any messages.
		int addfriend = friendService.addFriend(userUsername, friendUsername);
		if (addfriend == -5)
		{
			model.addAttribute("friendError", "Friend Request Error");
		}
		else if (addfriend == -4)
		{
			model.addAttribute("friendError", "You cannot friend request yourself: "+userUsername);
		}
		else if (addfriend == -3)
		{
			model.addAttribute("friendError", "Friend Request Already Sent to: "+friendUsername);
		}
		else if (addfriend == -2)
		{
			model.addAttribute("friendError", "Friend Request Already Received From: "+friendUsername+" Accept Current Request Instead." );
		}
		else if (addfriend == -1){
			model.addAttribute("friendError", "Already Friends with User: "+friendUsername);
		}
		else if (addfriend == 0){
			model.addAttribute("friendError", "User was not found: " +friendUsername);
		}else if (addfriend == 1){
			model.addAttribute("success", "Success, Request was sent to: "+friendUsername);
		}

		return display(model, principal);
	}
	
	/**
	 * removeFriend, removes friend from user's friends via button click on friends name
	 * @param friendUsername - friend's username (provided by javascript via button click)
	 * @param model - model to attach attributes to.
	 * @param principal - Security User Object provided by Spring Security
	 * @return display()- friends page with updated details
	 */
	@PostMapping("/removeFriend")
	public String removeFriend(@RequestParam(name="friendUsername") String friendUsername, Model model, Principal principal)
	{
		String userUsername = principal.getName();
		
		int removeFriend = friendService.removeFriend(userUsername, friendUsername);
		//attempt to remove a friend and return results with any messages.
		if (removeFriend > 0){
			model.addAttribute("success", "Friend Removed.");
		}else{
			model.addAttribute("friendError", "Friend failed to remove");
		}
		
		return display(model, principal);
	}
	
	/**
	 * acceptRequest, accepts a friend request via button click on friends request
	 * @param friendUsername - friend's username (provided by javascript via button click)
	 * @param model - model to attach attributes to.
	 * @param principal - Security User Object provided by Spring Security
	 * @return display()- friends page with updated details
	 */
	@PostMapping("/acceptRequest")
	public String acceptRequest(@RequestParam(name="friendUsername") String friendUsername, Model model, Principal principal)
	{
		String userUsername = principal.getName();
		System.out.println(userUsername + " accepted " + friendUsername + "'s friend request");
		
		int acceptRequest = friendService.acceptRequest(userUsername, friendUsername);
		//attempt to accept a request and return results with any messages.
		if (acceptRequest > 0){
			model.addAttribute("success", "Request Accepted.");
		}else{
			model.addAttribute("friendError", "Request Failed To Accept");
		}
		
		return display(model, principal);
	}

	/**
	 * removeRequest, removes friend request via button click on friends request
	 * @param friendUsername - friend's username (provided by javascript via button click)
	 * @param model - model to attach attributes to.
	 * @param principal - Security User Object provided by Spring Security
	 * @return display()- friends page with updated details
	 */
	@PostMapping("/removeRequest")
	public String removeRequest(@RequestParam(name="friendUsername") String friendUsername, Model model, Principal principal)
	{
		String userUsername = principal.getName();
		
		int removeRequest = friendService.denyRequest(userUsername, friendUsername);
		//attempt to remove a request and return results with any messages.
		if (removeRequest > 0){
			model.addAttribute("success", "Request Denied.");
		}else{
			model.addAttribute("friendError", "Friend Request Failed to Remove");
		}
		
		return display(model, principal);

	}
}