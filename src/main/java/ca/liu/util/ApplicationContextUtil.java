package ca.liu.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public enum ApplicationContextUtil {
	instance;
	private ClassPathXmlApplicationContext cfx;
	private String contextFileName = "applicationContext.xml";
	
	private void buildApplicationContext() {
		cfx = new ClassPathXmlApplicationContext(contextFileName);
	}
	
	private void buildApplicationContext(Class<?> clazz) {
		cfx = new ClassPathXmlApplicationContext(contextFileName, clazz);
	}
	
	public ClassPathXmlApplicationContext getApplicationContext() {
		if(cfx == null) {
			buildApplicationContext();
		}
		return cfx;
	}
	
	public ClassPathXmlApplicationContext getApplicationContext(Class<?> clazz) {
		if(cfx == null) {
			buildApplicationContext(clazz);
		}
		return cfx;
	}
	
	public ClassPathXmlApplicationContext getApplicationContext(String contextFileName) {
		if(!this.contextFileName.equals(contextFileName)) {
			this.contextFileName = contextFileName;
		}
		return getApplicationContext();
	}
	
	public ClassPathXmlApplicationContext getApplicationContext(String contextFileName, Class<?> clazz) {
		if(!this.contextFileName.equals(contextFileName)) {
			this.contextFileName = contextFileName;
		}
		return getApplicationContext(clazz);
	}
}
