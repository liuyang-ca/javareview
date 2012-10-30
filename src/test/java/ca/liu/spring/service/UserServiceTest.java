package ca.liu.spring.service;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ca.liu.spring.service.UserService;

/**
 * This class shows how to use spring IoC with XML and annotation injection
 * @author Leo
 *
 */
public class UserServiceTest {
	@Test
	public void addUserTest1() {
		BeanFactory factory = new ClassPathXmlApplicationContext("config.xml", this.getClass());
		UserService srv = (UserService) factory.getBean("userSrv");
		UserService srv2 = (UserService) factory.getBean("userSrv");
		srv.printUser(null);
		System.out.println(srv == srv2);
	}
	
	@Test
	public void addUserTest2() {
		ca.liu.util.BeanFactory factory = ca.liu.util.ClassPathXmlApplicationContext.instance;
		UserService srv = (UserService) factory.getBean("userSrv");
		UserService srv2 = (UserService) factory.getBean("userSrv");
		srv.printUser(null);
		System.out.println(srv == srv2);
	}
	
	/**
	 * Test whether userDao ref has been inject into srv
	 */
	@Test
	public void InjectReferenceTest() {
		BeanFactory factory = new ClassPathXmlApplicationContext("collection.xml", this.getClass());
		UserService srv = (UserService) factory.getBean("userSrv");
		srv.getUserDAO().print(null);
	}
	
	/**
	 * Test whether name value has been inject into srv
	 */
	@Test
	public void InjectValueTest() {
		BeanFactory factory = new ClassPathXmlApplicationContext("collection.xml", this.getClass());
		UserService srv = (UserService) factory.getBean("userSrv");
		System.out.println(srv.getName());
	}
	
	/**
	 * Test whether name value has been inject into srv via annotation
	 */
	@Test
	public void annotationTest() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("annotation.xml", this.getClass());
		UserService srv = (UserService) ctx.getBean("userService");
		System.out.println(srv.getName());
	}
	
	/**
	 * Illustrate how to inject collection values
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void collectionTest() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("collection.xml", this.getClass());
		UserService srv = (UserService) ctx.getBean("userSrv");
		Properties props = srv.getProps();
		
		System.out.println("-------props---------");
		for(Object key : props.keySet()) {
			System.out.println(key + " : " + props.get(key));
		}
		
		System.out.println("-------list---------");
		System.out.println(Arrays.toString(srv.getList().toArray()));
		
		Map map = srv.getMap();
		System.out.println("-------map---------");
		for(Object key : map.keySet()) {
			System.out.println(key + " : " + map.get(key));
		}
		
		System.out.println("-------set---------");
		System.out.println(Arrays.toString(srv.getSet().toArray()));	
		
		ctx.destroy();
	}
}
