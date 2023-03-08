package com.milestone.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * Model to be used when logging in
 * @author Michal Uchmanowicz
 *
 */
public class LoginModel 
{

	@NotNull(message="User name is a required field")
	@Size(min=1, max=32, message="User Name must be between 4 and 32 characters")
	private String username;
	
	@NotNull(message="Password is a required field")
	@Size(min=1, max=32, message="Password must be between 1 and 32 characters")
	private String password;
	
	/**
	 * Nondefault constructor for loginModel
	 * @param username - username of loginmodel
	 * @param password - password of loginmodel
	 */
	public LoginModel(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	/**
	 * default constructor for loginmodel
	 */
	public LoginModel()
	{
		this.username = "";
		this.password = "";
	}
	/**
	 * Overridden toString method for LoginModel
	 */
    @Override
	public String toString() {
		return "LoginModel [username=" + username + ", password=" + password + "]";
	}
	/**
	 * getUsername returns username of model
	 * @return username - username of model
	 */
	public String getUsername()
	{
		return username;
	}
	/**
	 * set username of model
	 * @param username - username of model
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}
	/**
	 * getUsername returns password of model
	 * @return password - password of model
	 */
	public String getPassword()
	{
		return password;
	}
	/**
	 * set password of model
	 * @param password - password of model
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
}
