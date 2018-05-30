package com.example.webdevsummer12018.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Topic {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  private String title;
  private String content;
  @ManyToOne
  @JsonIgnore
  private Lesson lesson;
  @OneToMany(mappedBy="topic")
  private List<Widget> widgets;
  
  
  public String getContent() {
	  return content;
	}
  public void setContent(String content) {
	this.content = content;
	}

  public int getId() {
	  return id;
	}
  public void setId(int id) {
	  this.id = id;
	  }
  public String getTitle() {
	  return title;
  }
  public void setTitle(String title) {
	  this.title = title;
  }
  public Lesson getLesson() {
	  return lesson;
  }
  public void setLesson(Lesson lesson) {
	  this.lesson = lesson;
  }
public List<Widget> getWidgets() {
	return widgets;
}
public void setWidgets(List<Widget> widgets) {
	this.widgets = widgets;
}
}
