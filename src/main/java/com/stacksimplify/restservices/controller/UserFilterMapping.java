package com.stacksimplify.restservices.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value = "/jackson/users")
@Validated
public class UserFilterMapping {
	
	@Autowired
	private UserService userService;
	
	
	// Get User By Id
	@GetMapping("/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") Long id) {
		try {
			Optional<User> optionalUser = userService.getUserById(id);	
			User user = optionalUser.get();
			MappingJacksonValue mapping = new MappingJacksonValue(user);
			
			Set<String> fields = new HashSet<String>();
			fields.add("firstname");
			fields.add("email");
			
			
			FilterProvider filters = new SimpleFilterProvider()
						.addFilter("userFieldFiltered", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			mapping.setFilters(filters );
			
			return mapping;
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	
	
	
	// Get User By Id
	@GetMapping("/param/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") Long id, @RequestParam Set<String> fields) {
		try {
			Optional<User> optionalUser = userService.getUserById(id);	
			User user = optionalUser.get();
			MappingJacksonValue mapping = new MappingJacksonValue(user);
			
			
			FilterProvider filters = new SimpleFilterProvider()
						.addFilter("userFieldFiltered", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			mapping.setFilters(filters );
			
			return mapping;
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

}






























