package com.stacksimplify.restservices.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="user")
//@JsonFilter(value = "userFieldFiltered")
public class User extends ResourceSupport{
	
	@Id
	@GeneratedValue
	@JsonView(Views.External.class)
	private Long userId;
	
	@JsonView(Views.External.class)
	@Column(name="USER_NAME", length =50 , nullable = false , unique = true)
	private String username;
	
	@JsonView(Views.External.class)
	@Column(name="FIRST_NAME", length=50, nullable = false)
	private String firstname;
	
	@JsonView(Views.External.class)
	@Column(name="LAST_NAME", length =50, nullable = false)
	private String lastname;
	
	@JsonView(Views.External.class)
	@Column(name="EMAIL_ADDRESS", length=50, nullable = false )
	private String email;
	
	@JsonView(Views.Internal.class)
	@Column(name="ROLE", length=50, nullable = false )
	private String role;
	
	@JsonView(Views.Internal.class)
	@Column(name="SSN", length=50, nullable = false )
	@JsonIgnore
	private String ssn;
	
	@JsonView(Views.Internal.class)
	@OneToMany(mappedBy = "user")
	private List<Order> orders;

	
	// Constructors 
	public User() {
	}



	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public User(Long userId, String username, String firstname, String lastname, String email, String role, String ssn,
			List<Order> orders) {
		super();
		this.userId = userId;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.orders = orders;
	}



	public void setId(Long id) {
		this.userId = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}



	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", orders=" + orders + "]";
	}


		
	
}
	
	
