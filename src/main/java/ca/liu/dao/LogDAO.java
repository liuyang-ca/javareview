package ca.liu.dao;

import ca.liu.domain.Log;
import ca.liu.domain.User;

public interface LogDAO extends EntityDAO<Log>{
	/**
	 * 
	 * @param userId
	 * @return number of deleted row
	 */
	public int deleteByUserId(User user);
}
