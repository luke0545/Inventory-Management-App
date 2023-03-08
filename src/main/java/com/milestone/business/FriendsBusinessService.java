package com.milestone.business;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.milestone.data.FriendAccessInterface;
import com.milestone.data.RegisterAccessInterface;
import com.milestone.model.FriendModel;

public class FriendsBusinessService implements FriendsBusinessServiceInterface{

	@Autowired
	FriendAccessInterface<FriendModel> friendsDAO;
	@Autowired
	RegisterAccessInterface userDAO;

	/**
	 * returns all friends by username
	 * @param username - name of user to get friends of.
	 * @return list of FriendModels
	 */
	@Override
	public List<FriendModel> getFriendsByUsername(String username) {
		
		return friendsDAO.getFriendsByUsername(username);
	}
	
	/**
	 * addFriend, adds a friend to friends list
	 * @param username - name of user requesting friend
	 * @param friendname - name of user being added
	 * @return success - int result of operation
	 */
	@Override
	public int addFriend(String username, String friendname) {

		List<FriendModel> friends = friendsDAO.getFriendsByUsername(username);
		
		//if user does not exist
		if (!userDAO.checkUserExists(friendname))
			return 0;
		
		//check if they are adding themselves
		if (friendname.equals(username))
			return -4;
		
		for (FriendModel friend : friends) 
		{			
			//check if you already requested
			if (friend.getFriend_username().equals(friendname) && friend.isRequested())
				return -3;
			
			//check if they already requested
			if (friend.getUser_username().equals(friendname) && friend.isRequested())
				return -2;
			
			//check if already friends
			if (friend.getFriend_username().equals(friendname) && friend.isAccepted()
			|| friend.getFriend_username().equals(username) && friend.isAccepted())
				return -1;
		}
		
		//otherwise, add friend
		if (friendsDAO.sendRequest(username, friendname)>0)
			return 1;
		else
			return -5;
	}
	
	/**
	 * remove friend, removes a friend from users list
	 * @param userUsername - user from whose list to remove friend
	 * @param friendUsername - name of friend to remove from users list
	 * @return success - int result of operation
	 */
	@Override
	public int removeFriend(String userUsername, String friendUsername) {

		int affected = friendsDAO.removeFriend(userUsername, friendUsername);
		if (affected != 0)
		{
			return affected;
		}
		return 0;
	}
	
	/**
	 * accept Request, accepts a users friend request by a user
	 * @param userUsername - user who accepts friend request
	 * @param friendUsername - friend who sends request
	 * @return success - int result of operation
	 */
	@Override
	public int acceptRequest(String username, String friendname) {

		List<FriendModel> friends = friendsDAO.getFriendsByUsername(username);
		
		// add friend
		if (friendsDAO.acceptRequest(username, friendname) > 0)
			return 1;
		else
			return 0;
	}
	
	/**
	 * deny Request, denies a users friend request by a user
	 * @param userUsername - user who denys friend request
	 * @param friendUsername - friend who sends request
	 * @return affected - int result of operation
	 */
	@Override
	public int denyRequest(String userUsername, String friendUsername) {

		int affected = friendsDAO.denyRequest(userUsername, friendUsername);

		return affected;
		
		
	}
	
	/**
	 * called when bean is created
	 */
	@Override
	public void init() 
	{
		System.out.println("ProductBusinessService Bean Instantiated");
	}
	/**
	 * called when bean is destroyed
	 */
	@Override
	public void destroy() 
	{
		System.out.println("ProductBusinessService Bean Destroyed");
	}


}
