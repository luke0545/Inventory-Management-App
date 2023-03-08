package com.milestone.data;

import java.util.List;

import com.milestone.model.FriendModel;
import com.milestone.model.ProductModel;

public interface FriendAccessInterface <T>{
	/**
	 * get Friends by user's name (parameter)
	 * @param username - user whose friends are retrieved
	 * @return	List<FriendModel>
	 */
	public List<FriendModel> getFriendsByUsername(String username);
	
	/**
	 * send request, sends a user a friend request
	 * @param username - username of user sending request
	 * @param friendname - username of user receiving request
	 * @return success - int result of operation
	 */
	public int sendRequest(String username, String friendname);
	
	/**
	 * accept request, accept a friend request as a user from friend
	 * @param username - username of user accepting request
	 * @param friendname - username of user receiving request
	 * @return success - int result of operation
	 */
	public int acceptRequest(String username, String friendname);
	
	/**
	 * deny request, deny a friend request as a user from friend
	 * @param username - username of user denying request
	 * @param friendname - username of user who sent request
	 * @return success - int result of operation
	 */
	public int denyRequest(String userUsername, String friendUsername);
	
	/**
	 * remove friend, removes a users friend.
	 * @param userUsername - name of user removing friend
	 * @param friendUsername - name of friend to be removed
	 * @return success - int result of operation
	 */
	public int removeFriend(String userUsername, String friendUsername);
	
	/**
	 * called when bean is created
	 */
	public void init();
	
	/**
	 * called when bean is destroyed
	 */
	public void destroy();


	


	


	


	
}

