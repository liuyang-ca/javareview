package ca.liu.jsf.bean;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.liu.domain.User;
import ca.liu.spring.service.UserService;

@Named
@SessionScoped
public class HelloBean implements Serializable {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	private static final long serialVersionUID = 1L;
	private String name;
	@Inject
	private UserService userService;
 
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String sayHello() {
		return "hello, " + name;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * This function deside redirect to user.xhtml or welcome.xhtml
	 * @return
	 */
	public String checkWelcome() {
		logger.info("userService = {}", userService);
		if(userService != null) {
			for(User u : userService.listUsers()) {
				if(u.getUsername().equals(getName())) {
					return "user";
				}
			}
		}
		
		return "welcome";
	}
}