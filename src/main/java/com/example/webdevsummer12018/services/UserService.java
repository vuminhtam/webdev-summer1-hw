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
	
//	@GetMapping("/api/findByUsername/{username}")  
//    public User findUserByUsername(@PathVariable("username") String username) throws IllegalArgumentException { 
//		List<User> query = (List<User>) repository.findUserByUsername(username);
//		if(query.size() != 0) {
//			return query.get(0);
//		}
//		else {
//			throw new IllegalArgumentException(username + " already exisits!");
//		} 
//    }  
	
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
	
	
	@GetMapping("/api/findByUsername/{username}")  
    public List<User> findUserByUsername(@PathVariable("username") String username) {  
		return (List<User>) repository.findUserByUsername(username);  
    }  
	
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session) {
		List<User> query = this.findUserByUsername(user.getUsername());
		if(query.size() == 0) {
			this.createUser(user);
			//session.setAttribute("user", user);
			return user;
		}
		else {
			throw new IllegalArgumentException(user.getUsername() + " already exisits!");
		}
	}

	
//	
//	@PostMapping("/api/register")
//	public User register(@RequestBody User user, HttpSession session) {
//		//try {
//			this.findUserByUsername(user.getUsername());
//			this.createUser(user);
//			session.setAttribute("user", user);
//			return user;
////		}
////		catch (IllegalArgumentException e) {
////			throw new IllegalArgumentException(e.getMessage() + " Cannot register");
////		}
//	}

	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		formatUser(user);
		return repository.save(user);
	}
	
	private void formatUser(User user) {
		if(user.getDOB().equals("")) {
			user.setDOB(null);
		}
	}

	@DeleteMapping("/api/user/{userID}")
	public void deleteUser(@PathVariable("userID") int userID) {
		repository.deleteById(userID);
	}

	
	@PutMapping("api/profile")
	public User updateUser(@RequestBody User newUser,
			HttpSession session) {
//		if(session.getAttribute("user") == null)  {
//			throw new IllegalArgumentException("Not logged in user.");
//		} else 
//			if (((User) session.getAttribute("user")).getId() != newUser.getId()) {
//			throw new IllegalArgumentException("Interal Error: not legit user");
//		}
		System.out.println(newUser.getId());
		User retrieve = this.findUserByID(newUser.getId());
		if(retrieve == null) {
			throw new IllegalArgumentException("Interal Error: cannot find user");
		}
		else {
			//update the user by id
			retrieve.setFirstName(newUser.getFirstName());
			retrieve.setLastName(newUser.getLastName());
			retrieve.setEmail(newUser.getEmail());
			retrieve.setPhone(newUser.getPhone());
			retrieve.setRole(newUser.getRole());
			retrieve.setDOB(newUser.getDOB());
			newUser.setId(retrieve.getId());
			repository.save(retrieve);
			//session.setAttribute("user", retrieve);
			return retrieve;//return the modified user
		}
	}
	
	@PostMapping("/api/login")  
	 public User login(@RequestBody User user, HttpSession session) {
		 List<User> query = (List<User>) repository.findUserByCredentials(user.getUsername(), user.getPassword());
		 if(query.size() == 0) {
			 throw new IllegalArgumentException(user.getUsername() + " does not exist!");
		 }
		 else {
			 session.setAttribute("user", user);
			 return query.get(0);
		 }
	 } 
	
	@PostMapping("/api/logout")  
	 public HttpSession logout(HttpSession session) {
		//session.invalidate();
		return session;
	 } 
}
