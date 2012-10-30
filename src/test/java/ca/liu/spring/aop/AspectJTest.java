package ca.liu.spring.aop;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ca.liu.dao.UserDAO;
import ca.liu.domain.User;

public class AspectJTest {
	private User user = new User("Liu Yang", "12345");
	
	@Test
	public void annotationAopTest() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("aop_annotation.xml", this.getClass());
		UserDAO dao = ctx.getBean("userDAO2", UserDAO.class);
		dao.print(user);
	}
	
	@Test
	public void aopTest() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("aop.xml", this.getClass());
		UserDAO dao = (UserDAO) ctx.getBean("userDAO");
		dao.print(user);
	}
}
