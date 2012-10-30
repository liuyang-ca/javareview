package ca.liu.spring.datasource;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import ca.liu.spring.service.UserService;
import ca.liu.util.StringUtil;

@ContextConfiguration("hibernate_transaction.xml")
public class HiberanteTransactionTest extends AbstractJUnit4SpringContextTests{
	@Inject @Named("sessionFactory")
	private SessionFactory sf;
	@Inject @Named("userSrvXML")
	private UserService srv;
	
	@Test
	public void dataSourceTest() {
		sf.openSession();
	}
	
	/**
	 * This test case use spring transaction manager annotation
	 */
	@Test
	public void printUsersTest() {
		srv.printUsers();
	}
	
	/**
	 * This test case shows how to partially commit correct data
	 */
	@Test
	public void resetAllUserPasswordTest() {	
		String ids = "1,2,3,4";
		for(int id : StringUtil.instance.parseIds(ids)) {
			try {
				srv.resetUserPassord(id, "222");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		srv.printUsers();
	}
	
	@Test
	public void resetUserPasswordTest() {
		srv.resetUserPassord(1, "5555");
		srv.printUsers();
	}
}
