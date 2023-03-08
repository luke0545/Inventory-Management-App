package com.milestone.business;

import com.milestone.model.UserModel;
/**
 * registration business service interface 
* @author Luke Walder
* 
*/
public interface RegistrationBusinessServiceInterface {
	
	/**
	 * used to access the DAO to check if a userModel exists in database
	 * @param user - userModel to check 
	 * @return boolean - does User Exist? true/false
	 */
	public boolean checkUserExists(UserModel user);
	/**
	 * used to access the DAO to add a userModel to database
	 * @param user - userModel to be added to database
	 * @return int - to show status of addUser
	 */
	public boolean addUser(UserModel user);
	
	/**
	 * used to access the DAO to add a user roles to database
	 * @param user - user to add roles to within the database
	 * @return int - to show status of addUser roles
	 */
	public boolean addUserRoles(UserModel user);
	/**
	 * used to access the DAO to delete user
	 * @param user - userModel to be deleted from database
	 * @return int - to show status of deleteUser
	 */
	public int deleteUser(UserModel user);
	/**
	 * 
	 * called when bean is created
	 */
	public void init();
	/**
	 * 
	 * called when bean is destroyed
	 */
	public void destroy();
    
}
