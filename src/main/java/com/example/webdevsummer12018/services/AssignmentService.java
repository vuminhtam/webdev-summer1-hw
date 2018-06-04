package com.example.webdevsummer12018.services;

import java.util.ArrayList;
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

import com.example.webdevsummer12018.models.Assignment;
import com.example.webdevsummer12018.models.Topic;
import com.example.webdevsummer12018.models.Widget;
import com.example.webdevsummer12018.repositories.AssignmentRepository;
import com.example.webdevsummer12018.repositories.TopicRepository;
import com.example.webdevsummer12018.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class AssignmentService {
	@Autowired 
	AssignmentRepository assignmentRepository;
	@Autowired 
	TopicRepository topicRepository;
	@Autowired 
	WidgetRepository widgetRepository;
	
	@GetMapping("/api/assignment")
	public List<Assignment> findAllAssignments() {
		return (List<Assignment>) assignmentRepository.findAll();
	}
	
	
	@GetMapping("/api/assignment/{wid}")
	public Widget findAssignmentByID(@PathVariable("wid") int id) {
		Optional<Assignment> res = assignmentRepository.findById(id);
		if(res.isPresent()) {
			return res.get();
		}
		else {
			return null;
		}
	}
	
	@GetMapping("/api/topic/{tid}/assignment")
	public List<Assignment> findAllAssignmentsByTopic(@PathVariable("tid") int tid) {
		Optional<Topic> res = topicRepository.findById(tid);
		if(res.isPresent()) {
			Topic topic = res.get();
			List<Assignment> assignments = new ArrayList<Assignment>();
			for(Widget w: topic.getWidgets()) {
				if(w.getWidgetType().equals("Assignment") || w instanceof Assignment) {
					assignments.add((Assignment) w);
				}
			}
			return assignments;
		}
		else {
			throw new IllegalArgumentException("Cannot find topic " + tid);
		}
	}
	
	@PostMapping("/api/topic/{id}/assignment")
	public void createAssignmentForTopic(
			@PathVariable("id") int id,
			@RequestBody Assignment assignment) {
		System.out.println("ADDDING");

		Optional<Topic> res = topicRepository.findById(id);
		if(res.isPresent()) {
			Topic topic = res.get();
			assignment.setTopic(topic);
			System.out.println(assignment.getTitle());
			assignmentRepository.save(assignment);
		}
		else {
			throw new IllegalArgumentException("Cannot find topic " + id);
		}
	}
	
	@DeleteMapping("/api/assignment/{id}")
	public void deleteWidgetByID(@PathVariable("id") int id) {
		assignmentRepository.deleteById(id);
	}
	
	
}
