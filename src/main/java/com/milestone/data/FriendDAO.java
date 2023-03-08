package com.milestone.data;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.milestone.model.FriendModel;
import com.milestone.model.UserModel;

@Service
public class FriendDAO implements FriendAccessInterface{

	@SuppressWarnings("unused")
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplaceObject;
	/**
	 * NonDefault Constructor
	 * @param dataSource - datasource to be used by JDBC
	 */
	public FriendDAO(DataSource dataSource) 
	{
		this.dataSource = dataSource;
		this.jdbcTemplaceObject = new JdbcTemplate(dataSource);
	}

	/**
	 * get Friends by user's name (parameter)
	 * @param username - user whose friends are retrieved
	 * @return	List<FriendModel>
	 */
	@Override
	public List<FriendModel> getFriendsByUsername(String username) {
		
		List<FriendModel> friends = new ArrayList<FriendModel>();
		String sql = "SELECT * FROM friends WHERE user_username = '"+username+"' OR friend_username = '"+username+"'";

		SqlRowSet srs = jdbcTemplaceObject.queryForRowSet(sql);
		while (srs.next())
		{
			friends.add(new FriendModel(srs.getString("user_username"),srs.getString("friend_username"),
					srs.getBoolean("requested"), srs.getBoolean("accepted")));
		}
		return friends;
	}
	
	/**
	 * send request, sends a user a friend request
	 * @param username - username of user sending request
	 * @param friendname - username of user receiving request
	 * @return success - int result of operation
	 */
	@Override
	public int sendRequest(String username, String friendname) {
		
		int rows;
		//sql statement
		String sql = "INSERT INTO friends (user_username, friend_username, requested, accepted) VALUES (?, ?, ?, ?)";
		rows = jdbcTemplaceObject.update(sql, username, friendname, 1, 0);
		
		return rows;
		
	}

	/**
	 * accept request, accept a friend request as a user from friend
	 * @param username - username of user accepting request
	 * @param friendname - username of user receiving request
	 * @return success - int result of operation
	 */
	@Override
	public int acceptRequest(String username, String friendname) {
		int rows = 0;

		// update request
		String sql = "UPDATE friends SET "
		+ "requested = '0',"
		+ "accepted = '1'"
		+ "WHERE ( user_username = '"+username 
		+ "' AND "+ "friend_username = '"+friendname
		+ "' AND "+ "requested = '1'"
		+ "  AND "+ "accepted = '0' )"
		+ " OR (user_username = '"+friendname
		+ "' AND "+ "friend_username = '"+username
		+ "' AND "+ "requested = '1'"
		+ "  AND "+ "accepted = '0' )";
		
		rows = jdbcTemplaceObject.update(sql);
		
		return rows;
	}

	/**
	 * deny request, deny a friend request as a user from friend
	 * @param username - username of user denying request
	 * @param friendname - username of user who sent request
	 * @return success - int result of operation
	 */
	@Override
	public int denyRequest(String userUsername, String friendUsername) {
		int rows;
		String sql = "DELETE FROM friends WHERE (user_username = '"+userUsername+"' AND friend_username = '"+friendUsername+"' AND requested = '1')"
				+ "OR (user_username = '"+friendUsername+"' AND friend_username = '"+userUsername+"' AND requested = '1')";
		
		rows = jdbcTemplaceObject.update(sql);

		return rows;
	}
	
	/**
	 * remove friend, removes a users friend.
	 * @param userUsername - name of user removing friend
	 * @param friendUsername - name of friend to be removed
	 * @return success - int result of operation
	 */
	@Override
	public int removeFriend(String userUsername, String friendUsername) {

		int rows;
		String sql = "DELETE FROM friends WHERE (user_username = '"+userUsername+"' AND friend_username = '"+friendUsername+"') "
				+ "OR (user_username = '"+friendUsername+"' AND friend_username = '"+userUsername+"')";
		
		rows = jdbcTemplaceObject.update(sql);

		return rows;
	}
	
	/**
	 * called when bean is created
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * called when bean is destroyed
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
