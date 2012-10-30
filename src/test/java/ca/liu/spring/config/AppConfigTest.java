package ca.liu.spring.config;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ca.liu.dao.UserDAO;
import ca.liu.spring.config.AppConfig;
import ca.liu.spring.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("config.xml")
public class AppConfigTest {	
	@Inject String userCountry;
	@Inject @Named("userSrvAppConfig") UserService userService;
	
	@Test
	public void listSystemProperties() {
		System.getProperties().list(System.out);
	}
	
	@Test
	public void userCountryTest() {
		System.out.println(userCountry);
	}
	
	@Test
	public void userServiceTest() {
		userService.printUsers();
	}
	
	@Test
	public void userDAOTest() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		ctx.registerShutdownHook();
		UserDAO userDAO = ctx.getBean("userDAOAppConfig", UserDAO.class);
		UserService srv = ctx.getBean(UserService.class);
		userDAO.print(null);
		System.out.println(srv.getName());
	}
}
