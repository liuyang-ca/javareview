package ca.liu.spring.entity;

import org.junit.Before;
import org.junit.Test;

import ca.liu.domain.Log;
import ca.liu.domain.User;

public class UserTest {
	private User user;
	
	@Before
	public void before() {
		user = new User("Liu Yang", "1234");
		user.getLogs().add(new Log(user, "User added"));
	}
	
	@Test
	public void toStringTest() {
		System.out.println(user);
	}
}
