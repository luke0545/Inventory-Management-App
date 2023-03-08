package com.milestone.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * Model to be used when registering
 * @author Michal Uchmanowicz
 *
 */
public class UserModel 
{
	
    LoginModel login = new LoginModel();
    
    @NotNull(message="First name is a required field")
	@Size(min=1, max=45, message="First name must be between 1 and 32 characters")
    String fName = "";

    @NotNull(message="Last name is a required field")
	@Size(min=1, max=45, message="Last Name must be between 1 and 32 characters")
    String lName = "";

    @NotNull(message="Email is a required field")
	@Size(min=1, max=45, message="Email must be between 1 and 32 characters")
    String email = "";

	@NotNull(message="Phone Number is a required field")
	@Size(min=10, max=10, message="Phone Number must be at least 10 characters with no - or ()")
    String phoneNumber = "";
    
	/**
	 * nondefault constructor for userModel
	 * @param fName - first name of user
	 * @param lName - last name of user
	 * @param username - username of user
	 * @param password - password of user
	 * @param email - email of user
	 * @param phoneNumber - phone of user
	 */
	public UserModel(String fName, String lName, String username, String password, String email, String phoneNumber) {
		this.fName = fName;
		this.lName = lName;
		login.setUsername(username);
		login.setPassword(password);
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * default constructor for userModel
	 * 
	 */
	public UserModel()
	{
		this.fName = "";
		this.lName = "";
		login.setUsername("");
		login.setPassword("");
		this.email = "";
		this.phoneNumber = "";
	}
	/**
	 * Overidden ToString method for UserModel
	 */
	@Override
	public String toString() {
		return "UserModel [ fName=" + fName + ", lName=" + lName + ", userName="
				+ this.getUsername() + ", password=" + this.getPassword() + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}
    /**
     * get fname returns fname of model
     * @return fname - fname of model
     */
	public String getfName() {
		return fName;
	}
	 /**
     * set fName sets fName of model
     * @param fName - fName of model
     */
	public void setfName(String fName) {
		this.fName = fName;
	}
    /**
     * get lname returns lname of model
     * @return lname - lname of model
     */
	public String getlName() {
		return lName;
	}
	 /**
     * set lname sets lname of model
	 * @param lName - lname of model
     */
	public void setlName(String lName) {
		this.lName = lName;
	}
    /**
     * get userName returns userName of model
     * @return userName - userName of model
     */
	public String getUsername() {
		return login.getUsername();
	}
	 /**
     * set userName sets userName of model
     * @param userName - userName of model
     */
	public void setUsername(String userName) {
		login.setUsername(userName);
		}
    /**
     * get password returns password of model
     * @return password - password of model
     */
	public String getPassword() {
		return login.getPassword();
	}
	 /**
     * set password sets password of model
     * @param password - password of model
     */
	public void setPassword(String password) {
		login.setPassword(password);
	}
    /**
     * get email returns email of model
     * @return email - email of model
     */
	public String getEmail() {
		return email;
	}
	 /**
     * set email sets email of model
     * @param email - email of model
     */
	public void setEmail(String email) {
		this.email = email;
	}
    /**
     * get phoneNumber returns phoneNumber of model
     * @return phoneNumber - phoneNumber of model
     */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	 /**
     * set phoneNumber sets phoneNumber of model
     * @param phoneNumber - phoneNumber of model
     */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
