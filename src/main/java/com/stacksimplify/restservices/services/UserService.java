package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistExecption;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class UserService {

	// Autowire UserRepository
	@Autowired
	private UserRepository userRepository;
	
	// Get all users
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	
	// Create user
	public User createUser(User user) throws UserExistExecption {
		User userExisting = userRepository.findByUsername(user.getUsername());
		if(userExisting != null) {
			throw new UserExistExecption("A user with the given name already exist");
		}
		return userRepository.save(user);
	}
	
	
	// Update User 
	public User updateUser(Long id , User user) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);
		if(!optionalUser.isPresent()) {
		  throw new UserNotFoundException("The user with the given Id doesnt exist");	
		}
		user.setUserId(id);
		return userRepository.save(user);
	}
	
	
	// Get userById
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("The User with the id: " + id + " Not found");
		}
		return user;
	}
	
	
	// Delete user found By Id
	public void deleteUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "The User with the id: " + id + " Not found");
		}
		  userRepository.deleteById(id);
	}
	
	
	// Find The user By username
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	
}
















