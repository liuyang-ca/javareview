package ca.liu.j2se.proxy;

import ca.liu.domain.User;
import ca.liu.spring.dao.hibernateImpl.UserDAOImplTwo;

public class UserDAOImplTwoInvocationHandler extends UserDAOImplTwo{
	@Override
	public void print(User user) {
		System.out.println("Before print user in " + this.getClass().getSimpleName());
		super.print(user);
		System.out.println("after print user in " + this.getClass().getSimpleName());
	}
}
