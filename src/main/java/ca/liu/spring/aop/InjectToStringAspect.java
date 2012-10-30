package ca.liu.spring.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import ca.liu.spring.annotation.InjectToString;

@Aspect
public class InjectToStringAspect {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private Gson gson;
	
	public InjectToStringAspect() {
		gson = new Gson();
	}
	
	@Around("@annotation(ca.liu.spring.annotation.InjectToString)")
	public String aroundInoke(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String methodName = signature.getMethod().getName();
		Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();
		
		try {
			Annotation[] annotations = joinPoint.getTarget().getClass().getMethod(methodName,parameterTypes).getAnnotations();
				
			for(Annotation a : annotations) {
				if(a.annotationType().equals(InjectToString.class)) {
					InjectToString annotation = (InjectToString) a;
					return toStringGenerator(joinPoint.getTarget(), annotation.excludeFields(), annotation.detailedCollection());
					//return toJson(joinPoint.getTarget(), annotation.excludeFields());
				}
			}
		} catch (NoSuchMethodException e) {
			logger.error(e.getClass().toString(), e);
		} catch (SecurityException e) {
			logger.error(e.getClass().toString(), e);
		}
		return "Error with InjectToString!";
	}
	
	/**
	 * 
	 * @param obj
	 * @param excludeFields 		a string array that contains field name that will be exclude from output 
	 * @param detailedCollection	false will only output list size
	 * @return
	 */
	protected String toJson(Object obj, String[] excludeFields) {
		List<String> list = excludeFields == null ? null : Arrays.asList(excludeFields);
		Field[] fields = obj.getClass().getDeclaredFields();
		if(fields.length > 0) {
			for(Field field : fields) {
				try {
					field.setAccessible(true);
					if(list != null && list.contains(field.getName())) {
						field.set(obj, null);
					}
				} catch (SecurityException e) {
					logger.error(e.getClass().toString(), e);
				} catch (IllegalArgumentException e) {
					logger.error(e.getClass().toString(), e);
				} catch (IllegalAccessException e) {
					logger.error(e.getClass().toString(), e);
				}
			}
		}
		return obj.getClass().getSimpleName() + gson.toJson(obj);	
	}
	
	/**
	 * 
	 * @param obj
	 * @param excludeFields 		a string array that contains field name that will be exclude from output 
	 * @param detailedCollection	false will only output list size
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected String toStringGenerator(Object obj, String[] excludeFields, boolean detailedCollection) {
		List<String> list = excludeFields == null ? null : Arrays.asList(excludeFields);
		StringBuilder sb = new StringBuilder();
		String str = "";
		Field[] fields = obj.getClass().getDeclaredFields();
		if(fields.length > 0) {
			for(Field field : fields) {
				try {
					field.setAccessible(true);
					Object fieldValue = field.get(obj);
					if(list == null || !list.contains(field.getName())) {
						if(!detailedCollection && fieldValue != null) {
							if(fieldValue instanceof Collection) {
								fieldValue = ((Collection)(fieldValue)).size();
								sb.append(field.getName()).append(".size:").append(fieldValue).append(", ");
							} else if(fieldValue instanceof Map) {
								fieldValue = ((Map)(fieldValue)).size();
								sb.append(field.getName()).append(".size:").append(fieldValue).append(", ");
							} else {
								sb.append(field.getName()).append(":").append(fieldValue).append(", ");	
							}
						} else {
							sb.append(field.getName()).append(":").append(fieldValue).append(", ");		
						}
					}
				} catch (SecurityException e) {
					logger.error(e.getClass().toString(), e);
				} catch (IllegalArgumentException e) {
					logger.error(e.getClass().toString(), e);
				} catch (IllegalAccessException e) {
					logger.error(e.getClass().toString(), e);
				}
			}
			if(sb.toString().endsWith(", ")) {
				str = sb.substring(0, sb.length()-2);
			}
		}
		return obj.getClass().getSimpleName() + "{" + str + "}";
	}
}
