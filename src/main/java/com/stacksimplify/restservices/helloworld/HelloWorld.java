package com.stacksimplify.restservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

	//@RequestMapping(method = RequestMethod.GET, path = "/hello")
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello from me 1:";
	}
	
	
	//userDetails
	@GetMapping("/hello-bean")
	public UserDetails userDetails() {
		// This will return a JSON object
		return new UserDetails("Mansour", "Malloum", "YDE 2");
	}
}
