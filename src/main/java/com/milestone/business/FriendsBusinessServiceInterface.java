package com.milestone.business;

import java.util.List;

import com.milestone.model.FriendModel;


public interface FriendsBusinessServiceInterface {
	
	/**
	 * returns all friends by username
	 * @param username - name of user to get friends of.
	 * @return list of FriendModels
	 */
	public List<FriendModel> getFriendsByUsername(String username);
	
	/**
	 * addFriend, adds a friend to friends list
	 * @param username - name of user requesting friend
	 * @param friendname - name of user being added
	 * @return success - int result of operation
	 */
	int addFriend(String username, String friendname);
	
	/**
	 * remove friend, removes a friend from users list
	 * @param userUsername - user from whose list to remove friend
	 * @param friendUsername - name of friend to remove from users list
	 * @return success - int result of operation
	 */
	public int removeFriend(String userUsername, String friendUsername);
	
	/**
	 * accept Request, accepts a users friend request by a user
	 * @param userUsername - user who accepts friend request
	 * @param friendUsername - friend who sends request
	 * @return success - int result of operation
	 */
	public int acceptRequest(String username, String friendname);

	/**
	 * deny Request, denies a users friend request by a user
	 * @param userUsername - user who denys friend request
	 * @param friendUsername - friend who sends request
	 * @return affected - int result of operation
	 */
	public int denyRequest(String userUsername, String friendUsername);

	/**
	 * called when bean is created
	 */
	public void init();
	
	/**
	 * called when bean is destroyed
	 */
	public void destroy();


	


	


	
}
