package com.milestone.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.milestone.data.RegisterAccessInterface;
import com.milestone.model.UserModel;

/**
 * service to interface between the controller and dao interface
* @author Luke Walder
* 
*/
public class RegistrationBusinessService implements RegistrationBusinessServiceInterface
{
	@Autowired
	RegisterAccessInterface DAO;
	
	/**
	 * used to access the DAO to check if a userModel exists in database
	 * @param user - userModel to check 
	 * @return boolean - does User Exist? true/false
	 */
	@Override
	public boolean checkUserExists(UserModel user)
	{
		return DAO.checkUserExists(user.getUsername());
	}
	/**
	 * used to access the DAO to add a userModel to database
	 * @param user - userModel to be added to database
	 * @return int - to show status of addUser
	 */
	@Override
	public boolean addUser(UserModel user)
	{
		return DAO.createUser(user);
	}
	
	/**
	 * used to access the DAO to add a user roles to database
	 * @param user - user to add roles to within the database
	 * @return int - to show status of addUser roles
	 */
	@Override
	public boolean addUserRoles(UserModel user)
	{
		return DAO.createUserRoles(user);
	}
	
	/**
	 * used to access the DAO to delete user
	 * @param user - userModel to be deleted from database
	 * @return int - to show status of deleteUser
	 */
	@Override
	public int deleteUser(UserModel user) 
	{
		return DAO.deleteUserByUsername(user);
	}
	/**
	 * 
	 * called when bean is created
	 */
	@Override
	public void init() 
	{
		System.out.println("RegistrationBusinessService Bean Instantiated ");
	}
	/**
	 * 
	 * called when bean is destroyed
	 */
	@Override
	public void destroy() 
	{
		System.out.println("RegistrationBusinessService Bean Destroyed");
	}

}
