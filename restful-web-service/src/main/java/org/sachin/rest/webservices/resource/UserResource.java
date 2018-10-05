package org.sachin.rest.webservices.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.sachin.rest.webservices.entity.User;
import org.sachin.rest.webservices.exception.UserNotFoundException;
import org.sachin.rest.webservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> retreiveUsers() {
		return userService.findAll();
	}

	@GetMapping("/users/{id}")
	public Resource<User> retreiveUser(@PathVariable int id) {
		User user = userService.findOne(id);

		if (user == null) {
			throw new UserNotFoundException("User not found " + id);
		}

		Resource<User> resource = new Resource<User>(user);

		ControllerLinkBuilder linkTo = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retreiveUsers());

		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User save = userService.save(user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(save.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {

		User user = userService.deleteById(id);

		if (user == null) {
			throw new UserNotFoundException("User not found " + id);
		}

	}
}
