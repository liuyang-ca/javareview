package ca.liu.spring.dao.hibernateImpl;

import javax.inject.Inject;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ca.liu.dao.LogDAO;
import ca.liu.domain.Log;
import ca.liu.domain.User;

@Repository("logDAO")
public class LogDAOImpl extends EntityDAOImpl<Log> implements LogDAO {
	@Inject private SessionFactory sessionFactory;
	
	@Override
	public String getFullClassName() {
		return Log.class.getCanonicalName();
	}
	
	@Override
	public void save(Log log) {
		super.save(log);
		if(log.getValue() == null || log.getValue().trim().equals("")) {
			throw new HibernateException("Log value cannot be empty or null!");
		}
	}

	public int deleteByUserId(User user) {
		return getCurrentSession().createQuery("delete Log l where l.user = :user")
				.setEntity("user", user)
		        .executeUpdate();	
	}

	@Override
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}