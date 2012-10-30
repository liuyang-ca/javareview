package ca.liu.spring.el;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("el.xml")
public class CustomerRegexTest {
	@Inject private CustomerRegex customerRegex;
	@Inject private EmailRegex emailRegex;
	
	@Test
	public void toStringPrimitiveTest() {
		//System.out.println(toStringGenerator(emailRegex, new String[]{"customerRegex"}, true));
		System.out.println(toStringGenerator(customerRegex));
		//System.out.println(toStringGenerator(5));
	}
	
	@Test
	public void customerRegexToStringTest() {
		System.out.println(customerRegex.toString());
	}
	
	@Test
	public void emailRegexToStringTest() {
		System.out.println(emailRegex);
	}
	
	@Test
	public void sandbox() {
		emailRegex.customerRegex = null;
		System.out.println(new Gson().toJson(emailRegex));
	}
	
	private String toStringGenerator(Object obj) {
		return toStringGenerator(obj, new String[]{}, false);
	}
	/**
	 * 
	 * @param obj
	 * @param excludeFields -- a string array that contains field name that will be exclude from output 
	 * @param detailedCollection -- false will only output list size
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String toStringGenerator(Object obj, String[] excludeFields, boolean detailedCollection) {
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(sb.toString().endsWith(", ")) {
				str = sb.substring(0, sb.length()-2);
			}
		}
		return obj.getClass().getSimpleName() + "{" + str + "}";
	}
}
