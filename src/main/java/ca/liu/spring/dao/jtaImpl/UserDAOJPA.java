package ca.liu.spring.dao.jtaImpl;

import ca.liu.dao.UserDAO;
import ca.liu.domain.User;

public interface UserDAOJPA extends UserDAO {
	public User findByFieldSingleResult(String fieldName, Object fieldValue);
}
