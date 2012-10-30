package ca.liu.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ca.liu.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context.xml")
public class UserServiceTest2 {
	@Inject
	private UserService userService;
	private User user;
	
	@Before
	public void init() {
		user = new User("DAO Test", "12345");
	}
	
	@Test
	public void listTest() {
		List<User> list = userService.listUsers();
		for(Object obj : list) {
			System.out.println(obj);
		}
	}
	
	@Test
	public void findByIdTest() {
		User user = userService.findById(1);
		System.out.println(user);
	}
}
