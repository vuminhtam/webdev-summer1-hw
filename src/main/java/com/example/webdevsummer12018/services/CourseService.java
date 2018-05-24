package com.example.webdevsummer12018.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer12018.models.Course;
import com.example.webdevsummer12018.models.User;
import com.example.webdevsummer12018.repositories.CourseRepository;
import com.example.webdevsummer12018.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		return courseRepository.findAll(); 
	}
	
	@GetMapping("/api/course/{courseId}")
	public Course findCourseByID(@PathVariable("courseId") int id) {
		Optional<Course> res = courseRepository.findById(id);
		if(res.isPresent()) {
			return res.get();
		}
		else {
			return null;
		}
	}

	@GetMapping("api/user/{userID}/course")
	public List<Course> findCoursesByUID(@PathVariable("userID") int userID) {
		Optional<User> res = userRepository.findById(userID);
		if(res.isPresent()) {
			User user = res.get();
			return user.getCourses();
		}
		else {
			throw new IllegalArgumentException("User " + userID + "not found");
		}
	}

	@PostMapping("/api/user/{userId}/course")
	public Course createCourseForUser(@PathVariable("userId") int uid, @RequestBody Course course) {
		Optional<User> res = userRepository.findById(uid);
		if(res.isPresent()) {
			User user = res.get();
			course.setCreated(new Date(new java.util.Date().getTime()));
			course.setModified(new Date(new java.util.Date().getTime()));
			course.setOwner(user);
			return courseRepository.save(course);
		}
		else {
			throw new IllegalArgumentException("User " + uid + "not found");
		}
	}
	
	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course) {
		course.setCreated(new Date(new java.util.Date().getTime()));
		course.setModified(new Date(new java.util.Date().getTime()));
		return courseRepository.save(course);
	}

	@DeleteMapping("/api/course/{courseId}")
	public void deleteCourse(@PathVariable("courseId") int id) {
		courseRepository.deleteById(id);
	}
}
