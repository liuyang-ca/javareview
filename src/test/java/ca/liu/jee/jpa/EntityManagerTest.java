package ca.liu.jee.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ca.liu.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("jtaImpl.xml")
public class EntityManagerTest {
	@PersistenceUnit(unitName = "localUnit")
	private EntityManagerFactory emf;
	private EntityManager em;
	
	@Before
	public void before() {
		emf = Persistence.createEntityManagerFactory("localUnit");
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}
	
	@After
	public void after() {
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	@Test
	public void findTest() {
		User foundUser = em.find(User.class, 1);
		System.out.println(foundUser);
	}
	
	@Test
	public void listTest() {
		CriteriaQuery<User> criteria = emf.getCriteriaBuilder().createQuery( User.class );
		Root<User> userRoot = criteria.from( User.class );
		criteria.select( userRoot );	
		
		List<User> list = em.createQuery(criteria).getResultList();
		for(Object obj : list) {
			System.out.println(obj);
		}
	}
}
