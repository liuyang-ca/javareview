package ca.liu.spring.aop;

import javax.inject.Named;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Named
public class UserDAOAspect {
	@Pointcut("execution(public * ca.liu.spring.dao..*.*(..))")
	public void myPointCut() {}
	
	@Before("myPointCut()")
	public void beforeInvoke() {
		System.out.println("AspectJ before invoke!");
	}

	@After("myPointCut()")
	public void afterInvoke() {
		System.out.println("AspectJ after invoke!");
	}
	
	@Around("myPointCut()") 
	public void aroundInoke(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("AspectJ around start invoke!");
		pjp.proceed();		
		System.out.println("AspectJ around end invoke!");
	}
}
