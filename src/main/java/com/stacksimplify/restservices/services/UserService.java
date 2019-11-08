package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.stacksimplify.restservices.entities.User;
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
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	
	
	// Get userById
	public Optional<User> getUserById(Long id){
		Optional<User> user = userRepository.findById(id);
		return user;
	}
	
	
	// Delete user found By Id
	public void deleteUserById(Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
	}
	
	
	// Find The user By username
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	
}
















