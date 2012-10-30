package ca.liu.spring.oxm;

import java.io.IOException;
import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("oxm.xml")
public class XMLConverterTest {
	@Inject private XMLConverter xmlConverter;
	
	@Test
	public void marshalTest() throws IOException {
		Customer customer = new Customer();
		customer.setAddress("123 Toronto");
		customer.setAge(23);
		customer.setFlag(true);
		customer.setName("Liu Yang");
		xmlConverter.convertFromObjectToXML(customer, System.out);
	}
	
	@Test
	public void unmarshalTest() throws IOException {
		Customer customer = (Customer) xmlConverter.convertFromXMLToObject("src/test/java/ca/liu/spring/oxm/customer.xml");
		System.out.println(new Gson().toJson(customer));
	}
}
