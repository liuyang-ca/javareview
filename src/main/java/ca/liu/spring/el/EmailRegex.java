package ca.liu.spring.el;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;

import ca.liu.spring.annotation.InjectToString;

@Named
public class EmailRegex {
	@Inject
	public CustomerRegex customerRegex;
	
	// email regular expression
	private String emailRegEx = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)" +
			"*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@Value("abc@abc.ca")
	private String email;
	
	@Value("#{T(java.util.Arrays).asList('Value 1','Value 2','Value 3')}")
	private List<String> list;
	
	@InjectToString(detailedCollection = true)
	public String toString() {
		return null;
	}
	
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getEmailRegEx() {
		return emailRegEx;
	}

	public void setEmailRegEx(String emailRegEx) {
		this.emailRegEx = emailRegEx;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
