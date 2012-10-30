package ca.liu.spring.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

public class UserFormTest {
	@SuppressWarnings("rawtypes")
	@Test
	public void validateUserFormTest() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		UserForm userForm = new UserForm();
		userForm.setUsername("abcddd");
		//userForm.setPassword("123445");
		Set<ConstraintViolation<UserForm>> constraintViolations = validator.validate(userForm);
		for(ConstraintViolation cv : constraintViolations) {
			System.out.println(cv);
		}
	}
}
