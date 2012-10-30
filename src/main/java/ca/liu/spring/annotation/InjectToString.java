package ca.liu.spring.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;  
  
import java.lang.annotation.Documented;  
import java.lang.annotation.Retention;  
import java.lang.annotation.Target;  

/**
 * 
 * @author Liu Yang
 * @param excludeFields 		a string array that contains fields name that will be exclude from output 
 * @param detailedCollection	a boolean value, false will only output list size
 */
@Retention(RUNTIME)  
@Target(METHOD)  
@Documented  
public @interface InjectToString {
	String[] excludeFields() default {};
	boolean detailedCollection() default false; 
}
