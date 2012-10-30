package ca.liu.spring.datasource;

import org.junit.Before;
import org.junit.Test;

import ca.liu.dao.LogDAO;
import ca.liu.dao.UserDAO;
import ca.liu.spring.service.UserService;
import ca.liu.util.ApplicationContextUtil;

/**
 * This class shows that @transactional only works if you get the instance by Spring IoC container.
 * @author Leo
 *
 */
public class TransactionTest {
	private UserService srv = new UserService();
	private UserService srvXML;
	
	@Before
	public void before() {
		UserDAO userDAO = ApplicationContextUtil.instance.getApplicationContext().getBean("userDAOXML", UserDAO.class);
		LogDAO logDAO = ApplicationContextUtil.instance.getApplicationContext().getBean("logDAOXML", LogDAO.class);
		srv.setUserDAO(userDAO);
		srv.setLogDAO(logDAO);
		srvXML = ApplicationContextUtil.instance.getApplicationContext().getBean(UserService.class);
	}
	
	/**
	 * Not working
	 */
	@Test
	public void printSrvUsersTest() {
		srv.printUsers();
	}
	
	/**
	 * Working
	 */
	@Test
	public void printSrvXMLUsersTest() {
		srvXML.printUsers();
	}
}
