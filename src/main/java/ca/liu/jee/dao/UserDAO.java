package ca.liu.jee.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaQuery;

import ca.liu.dao.EntityDAO;
import ca.liu.domain.User;

@Named("jeeUserDAO")
public class UserDAO implements EntityDAO<User>{
	@Inject private EntityManagerFactory emf;

	private EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void save(User transientInstance) {
		getEntityManager().persist(transientInstance);
	}

	public void delete(User transientInstance) {
		getEntityManager().remove(transientInstance);
	}

	public void update(User transientInstance) {
		getEntityManager().merge(transientInstance);
	}

	public User findById(int id) {
		return getEntityManager().find(User.class, id);
	}

	public List<User> list() {
		CriteriaQuery<User> criteria = emf.getCriteriaBuilder().createQuery(User.class);
		criteria.select(criteria.from(User.class));
		return getEntityManager().createQuery(criteria).getResultList();
	}
}
