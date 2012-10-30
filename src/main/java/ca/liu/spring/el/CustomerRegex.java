package ca.liu.spring.el;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;

import ca.liu.spring.annotation.InjectToString;

@Named
public class CustomerRegex {
		@Inject
		public EmailRegex emailRegex;
	
		@Value("#{'liuyang'.toUpperCase()}")
		private String name;
	
		// if this is a digit?
		@Value("#{'100' matches '\\d+' }")
		private boolean validDigit;
	 
		// if this is a digit + ternary operator
		@Value("#{ ('100' matches '\\d+') == true ? 'yes this is digit' : 'No this is not a digit'  }")
		private String msg;
	 
		// if this emailBean.emailAddress contains a valid email address?
		@Value("#{emailRegex.email matches emailRegex.emailRegEx}")
		private boolean validEmail;
		
		@Value("#{emailRegex.list[0]}")
		private String firstValue;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getFirstValue() {
			return firstValue;
		}

		public void setFirstValue(String firstValue) {
			this.firstValue = firstValue;
		}

		public boolean isValidDigit() {
			return validDigit;
		}

		public void setValidDigit(boolean validDigit) {
			this.validDigit = validDigit;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public boolean isValidEmail() {
			return validEmail;
		}

		public void setValidEmail(boolean validEmail) {
			this.validEmail = validEmail;
		}
		
		@InjectToString(excludeFields={"emailRegex"})
		public String toString() {
			return null;
		}
}
