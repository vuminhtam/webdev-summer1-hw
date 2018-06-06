package com.example.webdevsummer12018.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.webdevsummer12018.models.EssayQuestion;
import com.example.webdevsummer12018.models.Exam;
import com.example.webdevsummer12018.models.FillBlankQuestion;
import com.example.webdevsummer12018.models.MultipleChoiceQuestion;
import com.example.webdevsummer12018.models.Question;
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
	
	@GetMapping("/api/exam/{examId}/question")
	public List<Question> findAllQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<Question> questions = exam.getQuestions();
			return questions;
		}
		return null;
	}
	
	
	@GetMapping("/api/multi/{questionId}")
	public MultipleChoiceQuestion findMultiQuestionById(@PathVariable("questionId") int questionId) {
		Optional<MultipleChoiceQuestion> optional = mutiRepo.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@GetMapping("/api/truefalse/{questionId}")
	public TrueFalseQuestion findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
		Optional<TrueFalseQuestion> optional = trueFalseRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	
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
