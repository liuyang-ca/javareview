package ca.liu.jsf.bean;

import java.util.List;

import javax.inject.Inject;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import ca.liu.domain.User;

@ContextConfiguration("userBeanTestAnnotation.xml")
public class UserBeanTest extends AbstractJUnit4SpringContextTests {
	@Inject private UserBean userBean;

	@Test
	public void addTest() {
		userBean.setUsername("New Guy");
		userBean.setPassword("12345");
		userBean.addUser();
	}
	
	@Test
	public void listTest() {
		List<User> list = userBean.list();
		System.out.println(list.size());
		System.out.println("--------------------");
		for(Object obj : list) {
			System.out.println(obj);
		}
	}
}
