package com.stacksimplify.restservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimplify.restservices.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	// Find User By username
	User findByUsername(String username);
}
