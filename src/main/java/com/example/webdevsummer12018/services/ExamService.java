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
import com.example.webdevsummer12018.models.Exam;
import com.example.webdevsummer12018.models.MultipleChoiceQuestion;
import com.example.webdevsummer12018.models.Question;
import com.example.webdevsummer12018.models.Topic;
import com.example.webdevsummer12018.models.TrueFalseQuestion;
import com.example.webdevsummer12018.models.Widget;
import com.example.webdevsummer12018.repositories.AssignmentRepository;
import com.example.webdevsummer12018.repositories.ExamRepository;
import com.example.webdevsummer12018.repositories.MultipleChoicesQuestionRepository;
import com.example.webdevsummer12018.repositories.TopicRepository;
import com.example.webdevsummer12018.repositories.TrueFalseQuestionRepository;
import com.example.webdevsummer12018.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	TrueFalseQuestionRepository trueFalseRepository;
	@Autowired
	MultipleChoicesQuestionRepository mutiRepo;
	@Autowired 
	TopicRepository topicRepository;
	@Autowired 
	WidgetRepository widgetRepository;

	
	@GetMapping("/api/exam/{examId}/question")
	public List<Question> findAllQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<Question> questions = exam.getQuestions();
			int count = questions.size();
			return questions;
		}
		return null;
	}
	
	@GetMapping("/api/exam")
	public Iterable<Exam> findAllExam() {
		return examRepository.findAll();
	}
	
	@GetMapping("/api/exam/{examId}")
	public Exam findExamById(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if(optionalExam.isPresent()) {
			return optionalExam.get();
		}
		return null;
	}
	
	@GetMapping("/api/topic/{tid}/exam")
	public List<Exam> findAllExamByTopic(@PathVariable("tid") int tid) {
		Optional<Topic> res = topicRepository.findById(tid);
		if(res.isPresent()) {
			Topic topic = res.get();
			List<Exam> assignments = new ArrayList<Exam>();
			for(Widget w: topic.getWidgets()) {
				if(w.getWidgetType().equals("Exam") || w instanceof Exam) {
					assignments.add((Exam) w);
				}
			}
			return assignments;
		}
		else {
			throw new IllegalArgumentException("Cannot find topic " + tid);
		}
	}
	
	@PostMapping("/api/topic/{id}/exam")
	public void createExamForTopic(
			@PathVariable("id") int id,
			@RequestBody Exam exam) {
		Optional<Topic> res = topicRepository.findById(id);
		if(res.isPresent()) {
			Topic topic = res.get();
			exam.setTopic(topic);
			examRepository.save(exam);
		}
		else {
			throw new IllegalArgumentException("Cannot find topic " + id);
		}
	}
	
	@DeleteMapping("/api/assignment/{id}")
	public void deleteWidgetByID(@PathVariable("id") int id) {
		examRepository.deleteById(id);
	}
	
	
	//------------------------------------------------------------------------------------------
	
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
}
