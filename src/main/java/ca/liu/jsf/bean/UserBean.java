package ca.liu.jsf.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.liu.domain.User;
import ca.liu.spring.service.UserService;

/**
 * Note that Serializable cannot user with @Transactional on default Isolation
 * @author Liu Yang
 *
 */
@Named
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	private static final long serialVersionUID = 3149658330067142931L;
	@Inject
	private UserService userService;
	
	private String username;
	private String password;

	public String addUser() {
		userService.saveUser(new User(this.getUsername(), this.getPassword()));
		clearForm();
		
		return null;
	}

	//clear form values
	private void clearForm(){
		this.username = "";
		this.password = "";
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
	
	public List<User> list() {
		logUserServiceStatus();
		return userService.listUsers();
	}
	
	private void logUserServiceStatus() {
		logger.info("userService = {}", userService);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
