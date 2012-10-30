package ca.liu.spring.service;

import javax.inject.Named;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ApplicationContextAware will inject context into beans
 * @author Leo
 *
 */
@Named
public class UserServiceApplicationContextAwareTest implements
		ApplicationContextAware {

	private ApplicationContext ctx;
	
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.ctx = ctx;
	}
	
	@Test
	public void sandbox() {
		System.out.println(ctx);
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
		UserServiceApplicationContextAwareTest test = ctx.getBean(UserServiceApplicationContextAwareTest.class);
		test.sandbox();
	}
}
