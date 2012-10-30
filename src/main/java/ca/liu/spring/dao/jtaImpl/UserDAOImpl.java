package ca.liu.spring.dao.jtaImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import ca.liu.domain.User;
import ca.liu.spring.annotation.InjectLogger;

@Repository("userDAOJpa")
public class UserDAOImpl extends AbstractJpaDao<User> implements UserDAOJPA {
	@InjectLogger private Logger logger;
	@PersistenceContext//(unitName = "localUnit")
	private EntityManager entityManager;

	@Override
	public void save(User transientInstance) {
		super.persist(transientInstance);
	}

	@Override
	public void delete(User transientInstance) {
		super.remove(transientInstance);
	}

	@Override
	public void update(User transientInstance) {
		super.merge(transientInstance);
	}
	
	public User findByFieldSingleResult(String fieldName, Object fieldValue) {
		return super.executeQuerySingleResult("select user from User user where user." + fieldName + " = '" + fieldValue + "'");
	}

	@Override
	public User findById(int id) {
		return super.executeQuerySingleResult("select user from User user where user.id = " + id);
	}

	@Override
	public List<User> list() {
		return super.executeQuery("select user from User user", (Object[])null);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public boolean canBeMerged(User o) {
		return true;
	}

	@Override
	public void print(User user) {
		System.out.println("Print User in UserDAOImpl(jta): " + user);
		logger.debug("Print User in UserDAOImplOne: {}", user);
	}

	@Override
	public List<User> listByQuery(String hql) {
		return super.executeQuery(hql, (Object[])null);
	}
}