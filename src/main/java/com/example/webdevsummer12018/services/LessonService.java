package com.example.webdevsummer12018.services;

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
import com.example.webdevsummer12018.repositories.CourseRepository;
import com.example.webdevsummer12018.repositories.LessonRepository;
import com.example.webdevsummer12018.repositories.ModuleRepository;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ModuleRepository moduleRepository;
	
	@GetMapping("/api/course/{cid}/module/{mid}/lesson")
	public List<Lesson> findAllLessonsForModule(
			@PathVariable("cid") int cid,
			@PathVariable("mid") int mid) {
		//return (List<Lesson>) lessonRepository.findLessonsByModuleviaCourse(cid, mid);
		Optional<Course> data = courseRepository.findById(cid);
		if(data.isPresent()) {
			Course course = data.get();
			List<Module> allModules = course.getModules();
			for(Module m : allModules) {
				if(m.getId() == mid) {
					return m.getLessons();
				}
			}
			throw new IllegalArgumentException("Cannot find module");
		}
		throw new IllegalArgumentException("Cannot find course");	
	}
	
	
	@PostMapping("/api/course/{courseId}/module/{mid}/lesson")
	public Lesson createLesson(
			@PathVariable("courseId") int courseId,
			@PathVariable("mid") int mid,
			@RequestBody Lesson newLesson) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			List<Module> allModules = course.getModules();
			System.out.println(mid);
			for(Module m : allModules) {
				if(m.getId() == mid) {
					newLesson.setModule(m);
					return lessonRepository.save(newLesson);
				}
			}
			throw new IllegalArgumentException("Cannot find module");
		}
		throw new IllegalArgumentException("Cannot find course");	
	}
//	
//	@GetMapping("/api/module/{mid}")
//	public Module findModuleById(
//			@PathVariable("mid") int mid) {
//		Optional<Module> data = moduleRepository.findById(mid);
//		if(data.isPresent()) {
//			return data.get();
//		}
//		return null;		
//	}
//	
	
	@DeleteMapping("/api/lesson/{id}")
	public void deleteLesson(@PathVariable("id") int id) {
		System.out.println(id);
		lessonRepository.deleteById(id);
	}
}
