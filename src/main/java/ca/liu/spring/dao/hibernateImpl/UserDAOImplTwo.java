package ca.liu.spring.dao.hibernateImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ca.liu.dao.UserDAO;
import ca.liu.domain.User;

@Repository("userDAO2")
public class UserDAOImplTwo implements UserDAO {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	private HibernateTemplate hibernateTemplate;
	private SessionFactory superSessionFactory;
	
	public void delete(User transientInstance) {
		hibernateTemplate.delete(transientInstance);
	}

	public User findById(int id) {
		return hibernateTemplate.load(User.class, id);
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public SessionFactory getSessionFactory() {
		return this.superSessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<User> list() {
		return hibernateTemplate.find("from User");
	}

	public void print(User u) {
		System.out.println("Print User in UserDAOImplTwo: " + u);
	}

	public void save(User u) {
		hibernateTemplate.save(u);
		logger.debug("User {} is saved with hibernateTemplate!", u);
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.superSessionFactory = sessionFactory;
	}

	public void update(User transientInstance) {
		hibernateTemplate.update(transientInstance);
	}

	@Override
	public List<User> listByQuery(String hql) {
		// TODO Auto-generated method stub
		return null;
	}
}
