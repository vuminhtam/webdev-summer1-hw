package com.example.webdevsummer12018.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer12018.models.User;
import com.example.webdevsummer12018.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository repository;
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) this.repository.findAll();
	}
	
	@GetMapping("/api/findByUsername/{username}")  
    public List<User> findUserByUsername(@PathVariable("username") String username) {  
		return (List<User>) repository.findUserByUsername(username);  
    }  
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session) {
		List<User> query = this.findUserByUsername(user.getUsername());
		if(query.size() == 0) {
			this.createUser(user);
			session.setAttribute("user", user);
			return user;
		}
		else {
			throw new IllegalArgumentException(user.getUsername() + " already exisits!");
		}
	}

	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	@DeleteMapping("/api/user/{userID}")
	public void deleteUser(@PathVariable("userID") int userID) {
		repository.deleteById(userID);
	}
	
	@GetMapping("api/user/{userID}")
	public User findUserByID(@PathVariable("userID") int userID) {
		Optional<User> res = repository.findById(userID);
		if(res.isPresent()) {
			return res.get();
		}
		else {
			return null;
		}
	}
	
	@PutMapping("api/user/{userID}")
	public User updateUser(@PathVariable("userID") int userID, @RequestBody User newUser) {
		User retrieve = this.findUserByID(userID);
		if(retrieve.equals(null)) {
			return null; //cannot find the user id
		}
		else {
			//update the user by id
			retrieve.setFirstName(newUser.getFirstName());
			retrieve.setLastName(newUser.getLastName());
			repository.save(retrieve);
			return retrieve;//return the modified user
		}
	}
	
}
