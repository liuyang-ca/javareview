package ca.liu.spring.cache;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ca.liu.dao.UserDAO;
import ca.liu.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("cache.xml")
@Transactional
public class CacheTest {
	@Autowired
	private UserDAO userDAO;
	
	/**
	 * @Cacheable will cache criteria list
	 */
	@Test
	public void listTest() {
		List<User> list = userDAO.list();
		List<User> list2 = userDAO.list();
		Assert.assertEquals(list, list2);
	}
	
	/**
	 * @Cacheable will cache hql list
	 */
	@Test
	public void listByQueryTest() {
		List<User> list = userDAO.listByQuery("from User");
		List<User> list2 = userDAO.listByQuery("from User");
		Assert.assertEquals(list, list2);
	}
}
