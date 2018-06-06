package com.example.webdevsummer12018.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.webdevsummer12018.models.EssayQuestion;
import com.example.webdevsummer12018.models.Exam;
import com.example.webdevsummer12018.models.FillBlankQuestion;
import com.example.webdevsummer12018.models.MultipleChoiceQuestion;
import com.example.webdevsummer12018.models.Topic;
import com.example.webdevsummer12018.models.TrueFalseQuestion;
import com.example.webdevsummer12018.repositories.BaseQuestionRepository;
import com.example.webdevsummer12018.repositories.EssayRepository;
import com.example.webdevsummer12018.repositories.ExamRepository;
import com.example.webdevsummer12018.repositories.FillBlankRepository;
import com.example.webdevsummer12018.repositories.MultipleChoicesQuestionRepository;
import com.example.webdevsummer12018.repositories.TrueFalseQuestionRepository;

public class QuestionService {
	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	BaseQuestionRepository questionRepository;
	@Autowired
	TrueFalseQuestionRepository trueFalseRepository;
	@Autowired
	MultipleChoicesQuestionRepository mutiRepo;
	@Autowired
	EssayRepository essayRepository;
	@Autowired
	FillBlankRepository fillBlankRepository;
	//don't allow create base question
	
	@PostMapping("/api/exam/{eid}/essay")
	public void createEssayQForExam(
			@PathVariable("id") int id, @RequestBody EssayQuestion question) {
		Optional<Exam> res = examRepository.findById(id);
		if(res.isPresent()) {
			Exam exam = res.get();
			question.setExam(exam);
			essayRepository.save(question);
		}
		else {
			throw new IllegalArgumentException("Cannot find topic " + id);
		}
	}
	
	@PostMapping("/api/exam/{eid}/choice")
	public void createMCQForExam(
			@PathVariable("id") int id, @RequestBody MultipleChoiceQuestion question) {
		Optional<Exam> res = examRepository.findById(id);
		if(res.isPresent()) {
			Exam exam = res.get();
			question.setExam(exam);
			mutiRepo.save(question);
		}
		else {
			throw new IllegalArgumentException("Cannot find topic " + id);
		}
	}
	
	@PostMapping("/api/exam/{eid}/blanks")
	public void createFillBlankQForExam(
			@PathVariable("id") int id, @RequestBody FillBlankQuestion question) {
		Optional<Exam> res = examRepository.findById(id);
		if(res.isPresent()) {
			Exam exam = res.get();
			question.setExam(exam);
			fillBlankRepository.save(question);
		}
		else {
			throw new IllegalArgumentException("Cannot find topic " + id);
		}
	}
	
	@PostMapping("/api/exam/{eid}/truefalse")
	public void createTFQForExam(
			@PathVariable("id") int id, @RequestBody TrueFalseQuestion question) {
		Optional<Exam> res = examRepository.findById(id);
		if(res.isPresent()) {
			Exam exam = res.get();
			question.setExam(exam);
			trueFalseRepository.save(question);
		}
		else {
			throw new IllegalArgumentException("Cannot find topic " + id);
		}
	}
	
	@DeleteMapping("/api/question/{qid}")
	public void deleteQuestionByID(@PathVariable("id") int id) {
		questionRepository.deleteById(id);
	}
}
