package ca.liu.ws.rest.springmvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.liu.domain.User;
import ca.liu.spring.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	@Inject private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<User> getUsers() {
		return userService.listUsers();
	}
	
	@RequestMapping(value="/str", method = RequestMethod.GET)
	@ResponseBody
	public String getString() {
		return "get this string!";
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteUser(@PathVariable int id) {
		userService.delete(id);
		return "User with id " + id + " deleted!";
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable int id) {
		return userService.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String addUser(@RequestBody User user) {
		userService.saveUser(user);
		return "User " + user.getUsername() + " added!";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public String updateUser(@RequestBody User user) {
		userService.update(user);
		return "User " + user.getUsername() + " updated!";
	}
}
