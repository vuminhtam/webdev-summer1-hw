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
import com.example.webdevsummer12018.models.EssayQuestion;
import com.example.webdevsummer12018.models.Exam;
import com.example.webdevsummer12018.models.FillBlankQuestion;
import com.example.webdevsummer12018.models.MultipleChoiceQuestion;
import com.example.webdevsummer12018.models.Question;
import com.example.webdevsummer12018.models.Topic;
import com.example.webdevsummer12018.models.TrueFalseQuestion;
import com.example.webdevsummer12018.models.Widget;
import com.example.webdevsummer12018.repositories.BaseQuestionRepository;
import com.example.webdevsummer12018.repositories.EssayRepository;
import com.example.webdevsummer12018.repositories.ExamRepository;
import com.example.webdevsummer12018.repositories.FillBlankRepository;
import com.example.webdevsummer12018.repositories.MultipleChoicesQuestionRepository;
import com.example.webdevsummer12018.repositories.TrueFalseQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
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
	
	@GetMapping("/api/exam/{examId}/{questionType}")
	public List<Question> findQuestionsForExamByType(
			@PathVariable("examId") int examId, @PathVariable("questionType") String questionType) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<Question> questions = exam.getQuestions();
			return this.filterByType(questions, questionType);
		}
		return null;
	}
	
	private List<Question> filterByType(List<Question> questions, String type) {
		List<Question> questionsByType = new ArrayList<Question>();
		for(Question w: questions) {
			if(w.getType() != null && w.getType().equals(type)) {
				questionsByType.add(w);
			}
		}
		return questionsByType;
	}
	
	@GetMapping("/api/choice/{questionId}")
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
		else {
			throw new IllegalArgumentException("Cannot find question " + questionId);
		}
	}
	
	@GetMapping("/api/essay/{questionId}")
	public EssayQuestion findEssayQuestionById(@PathVariable("questionId") int questionId) {
		Optional<EssayQuestion> optional = essayRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@GetMapping("/api/blanks/{questionId}")
	public FillBlankQuestion findFillBlanksQuestionById(@PathVariable("questionId") int questionId) {
		Optional<FillBlankQuestion> optional = fillBlankRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	
	@PostMapping("/api/exam/{id}/essay")
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
	
	@PostMapping("/api/exam/{id}/choice")
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
	
	@PostMapping("/api/exam/{id}/blanks")
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
	
	@PostMapping("/api/exam/{id}/truefalse")
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
	
	@DeleteMapping("/api/question/{id}")
	public void deleteQuestionByID(@PathVariable("id") int id) {
		questionRepository.deleteById(id);
	}
	
	@GetMapping("/api/question/{id}")
	public Optional<Question> findQuestionByID(@PathVariable("id") int id) {
		return questionRepository.findById(id);
	}
	
	
	@PostMapping("/api/exam/{eid}/truefalse/{qid}")
	public TrueFalseQuestion updateTFQuestionByID(
			@RequestBody TrueFalseQuestion newQuestion, 
			@PathVariable("eid") int eid,
			@PathVariable("qid") int qid) {
		Optional<Exam> res = examRepository.findById(eid);
		if(res.isPresent()) {
			Optional<TrueFalseQuestion> retrieve = trueFalseRepository.findById(qid);
			if(retrieve.isPresent()) {
				trueFalseRepository.deleteById(qid);
				newQuestion.setId(qid);
				newQuestion.setExam(res.get());
				System.out.println("hello");
				return trueFalseRepository.save(newQuestion);
			}
			else {
				throw new IllegalArgumentException("Cannot find question " + qid);
			}
		}
		else {
			throw new IllegalArgumentException("Cannot find widget");
		}
	}
	
	
	@PostMapping("/api/exam/{eid}/{questionType}/{qid}")
	public Question updateQuestionByID(
			@RequestBody Question newQuestion, 
			@PathVariable("eid") int eid,
			@PathVariable("questionType") String questionType,
			@PathVariable("qid") int qid) {
		Optional<Exam> res = examRepository.findById(eid);
		if(res.isPresent()) {
			Optional<Question> retrieve = questionRepository.findById(qid);
			if(retrieve.isPresent()) {
				questionRepository.deleteById(qid);
				newQuestion.setId(qid);
				newQuestion.setExam(res.get());
				return questionRepository.save(newQuestion);
			}
			else {
				throw new IllegalArgumentException("Cannot find question " + qid);
			}
		}
		else {
			throw new IllegalArgumentException("Cannot find widget");
		}
	}
}
