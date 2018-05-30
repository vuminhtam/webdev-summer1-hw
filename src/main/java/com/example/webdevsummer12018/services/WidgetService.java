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
import com.example.webdevsummer12018.models.Topic;
import com.example.webdevsummer12018.models.User;
import com.example.webdevsummer12018.models.Widget;
import com.example.webdevsummer12018.repositories.LessonRepository;
import com.example.webdevsummer12018.repositories.TopicRepository;
import com.example.webdevsummer12018.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {
	@Autowired 
	WidgetRepository widgetRepository;
	@Autowired 
	TopicRepository topicRepository;
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) widgetRepository.findAll(); 
	}
	
	@GetMapping("/api/topic/{tid}/widget")
	public List<Widget> findAllWidgetsByLesson(@PathVariable("tid") int tid) {
		Optional<Topic> res = topicRepository.findById(tid);
		if(res.isPresent()) {
			Topic topic = res.get();
			return topic.getWidgets();
		}
		else {
			throw new IllegalArgumentException("Cannot find lesson");
		}
	}
	
	@GetMapping("/api/widget/{wid}")
	public Widget findWidgetByID(@PathVariable("wid") int id) {
		Optional<Widget> res = widgetRepository.findById(id);
		if(res.isPresent()) {
			return res.get();
		}
		else {
			return null;
		}
	}
	
	@DeleteMapping("/api/widget/{wid}")
	public void deleteWidgetByID(@PathVariable("wid") int id) {
		widgetRepository.deleteById(id);
	}
	
	@PostMapping("/api/widget/save")
	public void saveAll(@RequestBody List<Widget> newList) {
		widgetRepository.deleteAll();
		widgetRepository.saveAll(newList);
	}
	
	@PostMapping("/api/topic/{tid}/widget/save")
	public void saveAllWidgetForTopic(
			@PathVariable("tid") int tid,
			@RequestBody List<Widget> newList) {
		List<Widget> widgetsByTopic = this.findAllWidgetsByLesson(tid);
		for(Widget w: widgetsByTopic) {
			widgetRepository.deleteById(w.getId());
		}
		widgetRepository.saveAll(newList);
	}
	
	@PostMapping("/api/widget/{wid}")
	public Widget updateWidgetByID(
			@RequestBody Widget newWidget, 
			@PathVariable("wid") int id) {
		Optional<Widget> res = widgetRepository.findById(id);
		if(res.isPresent()) {
			Widget retrieve = res.get();
			if(newWidget.getWidgetType().equals("Heading")) {
				retrieve.setSize(newWidget.getSize());
				retrieve.setText(newWidget.getText());
			} else if(newWidget.getWidgetType().equals("Image")) {
				retrieve.setSource(newWidget.getSource());
				retrieve.setText(newWidget.getText());
			} else if(newWidget.getWidgetType().equals("Link")) {
				retrieve.setHref(newWidget.getHref());
				retrieve.setText(newWidget.getText());
			} else if(newWidget.getWidgetType().equals("Paragraph")) {
				retrieve.setText(newWidget.getText());
			} else if(newWidget.getWidgetType().equals("List")) {
				retrieve.setItems(newWidget.getItems());
				retrieve.setListType(newWidget.getListType());
			} 
			widgetRepository.save(retrieve);
			return retrieve;
		}
		else {
			throw new IllegalArgumentException("Cannot find widget");
		}
	}
}
