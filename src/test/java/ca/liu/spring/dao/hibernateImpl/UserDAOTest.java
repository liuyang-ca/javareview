package ca.liu.spring.dao.hibernateImpl;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ca.liu.dao.UserDAO;
import ca.liu.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("hibernateImpl.xml")
public class UserDAOTest {
	@Inject
	private UserDAO userDAO;
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
	public void printTest() {
		userDAO.print(user);
	}
	
	/**
	 * Spring seems will auto rollback
	 */
	@Test
	@Transactional()
	@Rollback(false)
	public void saveTest() {
		userDAO.save(user);
		listTest();
	}
}
