package ca.liu.spring.beanpostprocessor;
/** 
 * Auto injects the underlying implementation of logger into the bean with field 
 * having annotation <code>Logger</code>. 
 *  
 */  
import java.lang.reflect.Field;  
  
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;  
import org.springframework.beans.factory.config.BeanPostProcessor;  
import org.springframework.util.ReflectionUtils;  

import ca.liu.spring.annotation.InjectLogger;
  
import static org.springframework.util.ReflectionUtils.FieldCallback;  
  
public class LoggerInjectorPostProcessor implements BeanPostProcessor {  
    
	public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
		 ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback() {  
			 public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {  
				 // make the field accessible if defined private  
				 ReflectionUtils.makeAccessible(field);
				 if (field.getAnnotation(InjectLogger.class) != null) {  
					 field.set(bean, LoggerFactory.getLogger(bean.getClass()));  
				 }  
			   }  
			 });  
		 	return bean;  
		}  
	
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {  
		return bean;  
	}  
}  
