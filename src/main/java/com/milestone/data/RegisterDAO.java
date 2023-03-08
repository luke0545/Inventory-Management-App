package com.milestone.data;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.milestone.model.UserModel;

/**
 * RegisterDAO to access database and manipulate user table
 * implements RegisterAccessInterface
 * @author Luke Walder
 */
@SuppressWarnings("rawtypes")
@Service
public class RegisterDAO implements RegisterAccessInterface
{
    @Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	/**
	 * NonDefault Constructor
	 * @param dataSource - datasource to be used by JDBC
	 */
	public RegisterDAO(DataSource dataSource)
	{
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	
	/**
	 * used to access the DAO to check if a userModel exists in database
	 * @param user - userModel to check 
	 * @return boolean - does User Exist? true/false
	 */
	@Override
	public boolean checkUserExists(String username)
	{
		boolean userExists = false;
		
		String checkForUser = "SELECT * FROM user";
		
		SqlRowSet srs = jdbcTemplateObject.queryForRowSet(checkForUser);
		while(srs.next())
		{   
//if user username matches returned users' usernames
            if(username.equals(srs.getString("username")))
            {
                userExists = true;
            }
		}
		return userExists;
	}
	
    /**
     * used to create a user in database
     * @param userModel - user model to be created
	 * @return int - returns status of createUser
     */
    @Override
    public boolean createUser(UserModel userModel) 
    {
        boolean userCreated = false;
        
        String createUserSQL = "INSERT INTO user (fName, lName, username, password, email, phoneNumber) VALUES ('" + 
                                userModel.getfName() + "', '" + userModel.getlName() + "', '" + userModel.getUsername() + "', '" + userModel.getPassword() + 
                                "', '" + userModel.getEmail() + "', '" + userModel.getPhoneNumber() + "')";
//rows affected by insert statement above
        int rows = jdbcTemplateObject.update(createUserSQL);
        
        if (rows>0)
            userCreated = true;
            
        return userCreated;
    }
    
    /**
	 * used to create a user's roles by a UserModel passed in
	 * @param userModel - user to create roles for within database
	 * @return int - status of createUserRoles
	 */
    @Override
    public boolean createUserRoles(UserModel userModel)
    {
        boolean userRolesCreated = false;

        String createUserSQL = "INSERT INTO authorities (username, authority) VALUES ('" + 
                                userModel.getUsername() + "', 'ROLE_USER')";

//rows affected by insert statement above
		int rows = jdbcTemplateObject.update(createUserSQL);
        
        if (rows>0)
            userRolesCreated = true;
            
        return userRolesCreated;
    }
    /**
     * used to delete user in database
    * @param userModel - user to be deleted
    * @return int - returns status of deleteUser
    */
    @Override
    public int deleteUserByUsername(UserModel userModel) 
    {
        return 0;
    }

}