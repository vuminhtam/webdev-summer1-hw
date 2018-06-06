package com.example.webdevsummer12018.models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class FillBlankQuestion extends Question {
	@Column(nullable = false)
	private String variables;
	
	public String getVariables() {
		return variables;
	}
	public void setVariables(String variables) {
		this.variables = variables;
	}
}
