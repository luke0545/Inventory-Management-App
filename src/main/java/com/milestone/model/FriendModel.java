package com.milestone.model;


/**
 * FriendModel class
 * @author Michal Uchmanowicz
 *
 */
public class FriendModel 
{
	private String user_username;
    private String friend_username;
    private boolean requested;
	private boolean accepted;

	/**
	 * Nondefault constructor for friend Model
	 * @param user_username
	 * @param friend_username
	 * @param requested
	 * @param accepted
	 */
	public FriendModel(String user_username, String friend_username, boolean requested, boolean accepted) {
		super();
		this.user_username = user_username;
		this.friend_username = friend_username;
		this.requested = requested;
		this.accepted = accepted;
	}
	
	/*
	 * Default Constructor for Friend Model 
	 */
	public FriendModel() {
		super();
		this.user_username = "";
		this.friend_username = "";
		this.requested = false;
		this.accepted = false;
	}
	
    /**
     * Overwritten toString method
     */
	@Override
	public String toString()
	{
		return 		" User : "+this.getUser_username()+" \r\n "
				+ "Friend : "+this.getFriend_username()+" \r\n "
				+ "Requested : "+this.isRequested()+" \r\n "
				+ "Accepted : "+this.isAccepted()+ " \r\n ";
	}

	/**
	 * getter for user_username
	 * @return user_username
	 */
	public String getUser_username() {
		return user_username;
	}

	/**
	 * setter for user_username
	 * @param user_username
	 */
	public void setUser_username(String user_username) {
		this.user_username = user_username;
	}

	/**
	 * getter for friend_username
	 * @return friend_username
	 */
	public String getFriend_username() {
		return friend_username;
	}

	/**
	 * setter for friend_username
	 * @param friend_username
	 */
	public void setFriend_username(String friend_username) {
		this.friend_username = friend_username;
	}

	/**
	 * getter for requested
	 * @return requested
	 */
	public boolean isRequested() {
		return requested;
	}

	/**
	 * setter for requested
	 * @param requested
	 */
	public void setRequested(boolean requested) {
		this.requested = requested;
	}

	/**
	 * getter for accepted
	 * @return accepted
	 */
	public boolean isAccepted() {
		return accepted;
	}

	/**
	 * setter for accepted
	 * @param accepted
	 */
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}



}