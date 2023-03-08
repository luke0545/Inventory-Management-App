package com.milestone.data;

import com.milestone.model.UserModel;

/**
 * @author Luke Walder
 * Used to access database when implemented
 */
public interface RegisterAccessInterface 
{
	/**
	 * used to access the DAO to check if a userModel exists in database
	 * @param friendname - userModel to check 
	 * @return boolean - does User Exist? true/false
	 */
	public boolean checkUserExists(String friendname);
	
    /**
	 * used to create a user by a UserModel passed in
	 * @param userModel - user to be created
	 * @return int - status of createUser
	 */
    public boolean createUser(UserModel userModel);

    /**
	 * used to create a user's roles by a UserModel passed in
	 * @param userModel - user to create roles for within database
	 * @return int - status of createUserRoles
	 */
    public boolean createUserRoles(UserModel userModel);
    
    /**
	 * used to delete a UserModel passed in
	 * @param userModel - user to be deleted
	 * @return int - status of deleteUser
	 */
	public int deleteUserByUsername(UserModel userModel);
	
}
