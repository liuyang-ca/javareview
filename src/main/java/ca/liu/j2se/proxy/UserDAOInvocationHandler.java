package ca.liu.j2se.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import ca.liu.dao.UserDAO;

public class UserDAOInvocationHandler implements InvocationHandler {
	private Object obj;
	
	public Object getInstance() {
		return Proxy.newProxyInstance(UserDAOInvocationHandler.class.getClassLoader(),
				new Class[]{UserDAO.class},
				this);
	}

	public UserDAOInvocationHandler() {

	}
	
	public UserDAOInvocationHandler(Object obj) {
		this.setObj(obj);
	}
	
	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result;
		System.out.println("Before Invoke, intercept by InvocationHandler!");
		result = method.invoke(obj, args);
		System.out.println("After Invoke, intercept by InvocationHandler!");
		return result;
	}
}
