package ca.liu.spring.config;

import javax.inject.Inject;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.liu.dao.LogDAO;
import ca.liu.dao.UserDAO;
import ca.liu.spring.dao.hibernateImpl.LogDAOImpl;
import ca.liu.spring.dao.hibernateImpl.UserDAOImplOne;
import ca.liu.spring.service.UserService;

@Configuration
public class AppConfig {
	//private @Value("#{hibernate.connection.driver_class}") String driverClass;
	private @Value("#{systemProperties['user.country']}") String userCountry;
	private @Value("#{T(java.lang.System).getProperty('user.country')}") String userCountry2;
	@Inject private UserDAO userDAO;
	@Inject private LogDAO logDAO;
	
	@Bean(name = "userCountry")
	public String userCountry() {
		return userCountry;
	}
	
	@Bean(name = "userCountry2")
	public String userCountry2() {
		return userCountry2;
	}
	
	@Bean(name = "userSrvAppConfig")
	public UserService userService() {
		UserService srv = new UserService();
		srv.setUserDAO(userDAO);
		srv.setLogDAO(logDAO);
		return srv;
	}
	
//	@Bean(name = "userDAOAppConfig")
//	public UserDAO userDAO() {
//		return new UserDAOImplOne();
//	}
//	
//	@Bean(name = "logDAOAppConfig")
//	public LogDAO logDAO() {
//		return new LogDAOImpl();
//	}
//	
//	@Bean
//	public BasicDataSource dataSource() {
//		BasicDataSource source = new BasicDataSource();
//		source.setUrl("jdbc:postgresql://localhost:5432/springtest");
//		source.setDriverClassName("org.postgresql.Driver");
//		source.setUsername("springtest_ddl");
//		source.setPassword("spring");	
//		return source;
//	}
}
