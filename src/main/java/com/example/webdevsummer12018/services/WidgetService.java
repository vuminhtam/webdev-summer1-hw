package com.example.webdevsummer12018.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer12018.models.Widget;
import com.example.webdevsummer12018.repositories.WidgetRepository;

@RestController
public class WidgetService {
	@Autowired 
	WidgetRepository widgetRepository;
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) widgetRepository.findAll(); 
	}
}
