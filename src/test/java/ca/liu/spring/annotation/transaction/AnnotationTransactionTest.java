package ca.liu.spring.annotation.transaction;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import ca.liu.spring.service.UserService;
import ca.liu.util.StringUtil;

/**
 * This test case shows that XML application context can mixing with annotation style.
 * @author Liu Yang
 *
 */
@ContextConfiguration("annotation-transaction.xml")
public class AnnotationTransactionTest extends AbstractJUnit4SpringContextTests {
	@Inject @Named("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Inject @Named("userService")
	private UserService userService;
	
	@Test
	public void dataSourceTest() {
		sessionFactory.openSession();
	}
	
	@Test
	public void printUsersTest() {
		userService.printUsers();
	}
	
	@Test
	public void deleteLogsByUserIdTest() {
		System.out.println(userService.deleteLogs(1));
	}
	
	@Test
	public void addLogTest() {
		userService.addLog(1, "Add Log One");
		userService.addLog(1, "Add Log Two");
		userService.addLog(1, null);
		
		userService.printUsers();
	}
	
	/**
	 * Check if @Repository works, it should wrap HibernateException with DataAccessException.
	 * 
	 * All the stereotype annotation( @Component, @Repository, @Service, @Controller, @Named ) can do the wrap 
	 * if we specify PostProcessor in XML.
	 */
	@Test
	public void resetAllUserPasswordTest() {	
		String ids = "1,2,3,4";
		for(int id : StringUtil.instance.parseIds(ids)) {
			try {
				userService.resetUserPassord(id, "222");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		userService.printUsers();
	}
}
