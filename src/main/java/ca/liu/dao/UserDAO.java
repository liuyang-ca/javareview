package ca.liu.dao;

import java.util.List;

import ca.liu.domain.User;

public interface UserDAO extends EntityDAO<User>{
	public void print(User user);
	public List<User> listByQuery(String hql);
}
