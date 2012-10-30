package ca.liu.spring.dao.hibernateImpl;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import ca.liu.dao.UserDAO;
import ca.liu.domain.User;
import ca.liu.spring.annotation.InjectLogger;

@Repository("userDAO")
public class UserDAOImplOne extends EntityDAOImpl<User> implements UserDAO {
	@InjectLogger private Logger logger;
	@Inject private SessionFactory sessionFactory;
	
	@Override
	public void update(User user) {
		super.update(user);
		if(user.getId() == 3) {
			throw new HibernateException("AAA");
		}
	}
	
	@Override
	//@Cacheable(cacheName = "javareview")
	@Cacheable("javareview")
	public List<User> list() {
		return super.list();
	}
	
	@Cacheable("javareview")
	public List<User> listByQuery(String hql) {
		return super.listByQuery(hql);
	}
	
	@Override
	public String getFullClassName() {
		return User.class.getCanonicalName();
	}
	
	@Override
	public void save(User u) {
		super.save(u);
		logger.debug("User {} is saved with session in {}!", u, this.getClass().getName());
	}

	public void print(User u) {
		System.out.println("Print User in UserDAOImplOne: " + u);
		logger.debug("Print User in UserDAOImplOne: {}", u);
	}

	@Override
	protected Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
}
