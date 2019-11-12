package com.stacksimplify.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping("/hateoas/users")
@Validated
public class UserHateoasController{

	@Autowired
	private UserService userService;
	
	// Get User By Id
	@GetMapping("/{id}")
	public Resource<User> getUserById(@PathVariable("id") Long id) {
		try {
			Optional<User> userOpt = userService.getUserById(id);
			User user = userOpt.get();
			Long userId = user.getUserId();
			Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			user.add(selfLink);
			Resource<User> finalResouce = new Resource<User>(user);
			
			return finalResouce;
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
	
	// List All Users
	@GetMapping
	public Resources<User> getAllUsers() throws UserNotFoundException{
		List<User> allUsers = userService.getAllUsers();
		for(User user: allUsers) {
			Long userId = user.getUserId();
			Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			user.add(selfLink);
			Resources<Order> allOrders = ControllerLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userId);
			Link ordersLink = ControllerLinkBuilder.linkTo(allOrders).withRel("all-orders");
			user.add(ordersLink);
		}
		return new Resources<User>(allUsers);
	}
	
}






































