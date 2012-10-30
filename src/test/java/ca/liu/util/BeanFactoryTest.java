package ca.liu.util;

import org.junit.Test;

import ca.liu.util.ClassPathXmlApplicationContext;

public class BeanFactoryTest {
	@Test
	public void parseText() {
		System.out.println(ClassPathXmlApplicationContext.instance.getBean("userDAO").getClass());
	}
	
	private static long MAX = 10L;
	
	@Test
	public void getTmpDirTest() {
		for(Object obj : System.getProperties().keySet()) {
			System.out.println(obj + ", " + System.getProperty(obj.toString()));
		}
	}
	
	@Test
	public void stringTest() {
		String c = "a";
		for(long i = 0L; i < MAX; i++) {
			c = c + "bc";
		}
	}
	
	@Test
	public void stringTest2() {
		String c = "a";
		for(long i = 0L; i < MAX; i++) {
			c = c.concat("bc");
		}
	}
	
	@Test
	public void stringTest3() {
		String c = "a";
		StringBuffer sf = new StringBuffer(c);
		for(long i = 0L; i < MAX; i++) {
			sf.append("bc");
		}
		c = sf.toString();
	}
}
