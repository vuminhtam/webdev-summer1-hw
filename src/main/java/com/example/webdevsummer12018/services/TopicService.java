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

import com.example.webdevsummer12018.models.Course;
import com.example.webdevsummer12018.models.Lesson;
import com.example.webdevsummer12018.models.Module;
import com.example.webdevsummer12018.models.Topic;
import com.example.webdevsummer12018.repositories.CourseRepository;
import com.example.webdevsummer12018.repositories.LessonRepository;
import com.example.webdevsummer12018.repositories.ModuleRepository;
import com.example.webdevsummer12018.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicService {
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	TopicRepository topicRepository;
	
	@GetMapping("/api/course/{cid}/module/{mid}/lesson/{lid}/topic")
	public List<Topic> findAllLessonsForModule(
			@PathVariable("cid") int cid,
			@PathVariable("mid") int mid,
			@PathVariable("lid") int lid) {
		Optional<Module> data = moduleRepository.findById(mid);
		if(data.isPresent()) {
			Module module = data.get();
			List<Lesson> allLessons = module.getLessons();
			for(Lesson l : allLessons) {
				if(l.getId() == lid) {
					return l.getTopics();
				}
			}
			throw new IllegalArgumentException("Cannot find lesson");
		}
		throw new IllegalArgumentException("Cannot find module");	
	}
	
	
	@PostMapping("/api/course/{cid}/module/{mid}/lesson/{lid}/topic")
	public Topic createTopic(
			@PathVariable("lid") int lid,
			@RequestBody Topic topic) {
		Optional<Lesson> data = lessonRepository.findById(lid);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			topic.setLesson(lesson);
			topic.setContent("Empty content");
			return topicRepository.save(topic);
		}
		return null;
	}
	
	@GetMapping("/api/topic/{id}")
	public Topic findTopicById(
			@PathVariable("id") int id) {
		Optional<Topic> data = topicRepository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;		
	}
	
	@GetMapping("/api/topic")
	public List<Topic> findAllTopic() {
		return (List<Topic>) topicRepository.findAll();		
	}
	
	@DeleteMapping("/api/topic/{id}")
	public void deleteTopic(@PathVariable("id") int id) {
		topicRepository.deleteById(id);
	}
}
