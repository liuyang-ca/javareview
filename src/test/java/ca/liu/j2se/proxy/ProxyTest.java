package ca.liu.j2se.proxy;

import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ca.liu.dao.UserDAO;
import ca.liu.domain.User;
import ca.liu.j2se.proxy.UserDAOInvocationHandler;
import ca.liu.spring.dao.hibernateImpl.UserDAOImplTwo;
import ca.liu.util.HibernateUtil;

public class ProxyTest {
	private User user = new User("Liu Yang", "12345");
	
    @AfterClass
    public static void afterClass() {
        HibernateUtil.INSTANCE.shutdown();
    }
    
    
    /**
     * AOP using Proxy/InvocationHandler
     */
	@Test
	public void proxyTest() {
		UserDAOInvocationHandler handler = new UserDAOInvocationHandler(new UserDAOImplTwo());
		final UserDAO userDAO = (UserDAO) handler.getInstance();
		userDAO.print(user);		
	}

	/**
	 * AOP using inheritance
	 */
	@Test
	public void proxyTest2() {
		UserDAO userDAO = new UserDAOImplTwoInvocationHandler();
		userDAO.print(user);
	}
	
	
	/**
	 * AOP using Proxy/InvocationHandler and get it via injection
	 */
	@Test
	public void proxyInjectionTest() {
		BeanFactory factory = new ClassPathXmlApplicationContext("proxy.xml");
		UserDAO userDAO = factory.getBean("userDAO", UserDAO.class);
		UserDAO userDAO2 = factory.getBean("userDAO2", UserDAO.class);
		userDAO.print(user);
		userDAO2.print(user);
	}
}
