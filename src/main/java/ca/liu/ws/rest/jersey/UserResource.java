package ca.liu.ws.rest.jersey;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.springframework.stereotype.Controller;

import ca.liu.domain.User;
import ca.liu.spring.service.UserService;

@Controller
@Produces("application/xml")
@Path("/users")
public class UserResource {
	@Inject private UserService userService;
	
	@GET
	public List<User> getUsers() {
		return userService.listUsers();
	}
	
	@GET
	@Path("/{id}")
	public User getUser(@PathParam("id") int userId) {
		return userService.findById(userId);
	}

	@POST
	@Path("/add")
	@Produces("text/html")
	@Consumes("application/xml")
	public String addUser(User user) {
		userService.saveUser(user);
		return "User " + user.getUsername() + " added!";
	}
}
