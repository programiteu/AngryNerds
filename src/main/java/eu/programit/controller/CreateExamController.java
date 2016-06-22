package eu.programit.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.programit.domain.Exam;
import eu.programit.domain.Question;
import eu.programit.domain.TestAnswerForm;
import eu.programit.domain.TestQuestionForm;
import eu.programit.domain.TestResults;
import eu.programit.domain.TestViews;
import eu.programit.service.ICreateExamService;
import eu.programit.service.IQuestionService;

@Controller
public class CreateExamController {
	
	TestResults myTestResults = new TestResults();
	TestViews myTestView;
	@Autowired 
	ICreateExamService iCreateExamService;
	
	@Autowired
	IQuestionService iQuestionService;
	
	
	
	@RequestMapping (value= "/createexam", method = RequestMethod.POST)
	public String saveExam (@ModelAttribute("exam")Exam exam, Model model){
		System.out.println(exam);
		iCreateExamService.saveExam(exam);
		model.addAttribute("question", new Question());
		Iterable<Question> questions = iQuestionService.findAll();
		model.addAttribute("questions", questions);
		
		return "addquestionstoexam";
	}
	
	@RequestMapping (value= "/addquestionstoexam", method = RequestMethod.POST)
	public String saveExamQuestions(@ModelAttribute("examquestion")Question question, Model model){
		List<Exam> exams = (List<Exam>)iCreateExamService.findAll();
		Exam addedExam = exams.get(exams.size()-1);
//		question.setExam(addedExam);
		System.out.println("in de requestmapping" + question);
		iCreateExamService.saveQuestion(question);
		Iterable<Question> questions = iQuestionService.findAll();
		
//		List<Question> questions = addedExam.getQuestions();
		model.addAttribute("exam", addedExam);
		model.addAttribute("question", new Question());
		model.addAttribute("questions", questions);
		System.out.println("in de requestmapping" + questions);
		System.out.println("in de requestmapping" + addedExam);
		
		return "addquestionstoexam";
	}
	
//	@RequestMapping(value = "/savetestquestionstoexam", method = RequestMethod.POST)
//	public String saveTestQuestionsToExam(@ModelAttribute TestQuestionForm testQuestionForm, Principal principal) {
//		myTestResults.setTestResults(new Integer(myTestView.getCurrentQuestion().getQuestionId()),
//				testQuestionForm.getTestQuestions());
//		myTestView.getPrevQuestion();
//		myTestResults.printValues();
//		if (testQuestionForm.getTestQuestions() != null) {
//			for (int s : testQuestionForm.getTestQuestions()){
//				System.out.println("Answer = " + s + "gebruiker:" + principal.getName());
//			}
//		}
//		System.out.println(myTestResults);
//
//        return "redirect:/";
//	}

}
