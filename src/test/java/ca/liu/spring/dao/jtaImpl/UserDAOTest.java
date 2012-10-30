package ca.liu.spring.dao.jtaImpl;

import java.util.List;

import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ca.liu.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("jtaImpl.xml")
public class UserDAOTest {
	@Inject
	private UserDAOJPA userDAO;
	private User user;
	
	@Before
	public void init() {
		user = new User("DAO Test", "12345");
	}
	
	@Test
	@Transactional(readOnly = true)
	public void listTest() {
		List<User> list = userDAO.list();
		for(Object obj : list) {
			System.out.println(obj);
		}
	}
	
	@Test
	public void findByIdTest() {
		System.out.println(userDAO.findById(1));
	}
	
	@Test
	public void findByFieldTest() {
		System.out.println(userDAO.findByFieldSingleResult("username", "Jack Smith"));
	}
	
	@Test
	public void printTest() {
		userDAO.print(user);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void deleteTest() {
		userDAO.delete(userDAO.findById(13));
		listTest();
	}
	
	/**
	 * Spring seems will auto rollback
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void saveTest() {
		user.setId(16);
		user.setUsername("Changed DAO Test");
		userDAO.save(user);
		listTest();
	}
}
