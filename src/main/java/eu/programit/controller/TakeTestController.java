package eu.programit.controller;

import eu.programit.domain.*;
import eu.programit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TakeTestController {

	// int questionCounter = 1;
	TestResults myTestResults = new TestResults();
	TestViews myTestView;
	// List<TestViewsContent> myTestsList;

	@Autowired
	IQuestionService iQuestionService;

    @Autowired
	IUserService iUserService;
	
	@Autowired
	ITestResultService iTestResultService;

	@Autowired
	IAnswerService iAnswerService;

	@Autowired
	TestViewsService testViewsService;

	@RequestMapping(value = "/loadExamQuestion", method = RequestMethod.GET)
	public String LoadExamQuestion(Model model, TestAnswerForm testAnswerForm, Principal principal) {
		Question q;
		List<Answer> answers = null;
		try {
			// q = iQuestionService.findById(questionCounter);
			q = iQuestionService.findById(myTestView.getCurrentQuestion().getQuestionId());
			answers = q.getAnswers();
		} catch (NullPointerException npe) {
			q = new Question();
			q.setContent("Unknown question requested (questionID does not exist)");
		}
		model.addAttribute("numberCorrect", getCorrectAnswers(answers));
		// questionCounter++;
		// System.out.println("TakeTestCont: questionCounter = " +
		// questionCounter);
		model.addAttribute("question", q);// the 1 will get question 2 (index 0)

		model.addAttribute("answers", answers);
		model.addAttribute("mytestview", myTestView);
		model.addAttribute("mytestresults", myTestResults.getTestResults().get(q.getQuestionID()));
		System.out.println("TAkeTestController . mytestresults : " + myTestResults.getTestResults().get(q.getQuestionID()));
		// myTestResults.getTestResults(myTestView.getQuestionNr()));

		// model.addAttribute("selectedAnswers", selectedAnswers);

		return "ExamQuestion";
	}

	// Leandro: getNrOfCorrectAnswers komt volgens mij meer overeen met de functionaliteit
	private int getCorrectAnswers(List<Answer> answers) {
		// TODO Auto-generated method stub
		int count = 0;
		//System.out.println(answers);
		if (answers != null) {
			for (Answer answer : answers) {
				if (answer.isCorrect()) {
					count++;
				}
			}
		}
		System.out.println("waarde van count: " + count);

		return count;
	}

	@RequestMapping(value = "/loadExamQuestion", method = RequestMethod.POST)
	public String LoadExamQuestionPOST(@ModelAttribute TestAnswerForm testAnswerForm, Principal principal) {
		List<Integer> answ = testAnswerForm.getTestAnswers();
		if (answ != null) {
			for (int s : answ) {
				System.out.println("Answer = " + s + principal.getName());

			}
		}
		myTestResults.setTestResults(new Integer(myTestView.getCurrentQuestion().getQuestionId()),
				testAnswerForm.getTestAnswers());
		System.out.println(myTestResults);
		return "redirect:/loadExamQuestion";

	}

	// Select a Test
	// *********************************************************************

	@RequestMapping("/SelectTest")
	public String selectTest(Model model) {
		Iterable<TestViews> tv = testViewsService.findAll();
		model.addAttribute("testviews", tv);
		model.addAttribute("testview", new TestViews());
		return "SelectTest";
	}

	// Start a new Test
	// *********************************************************************

	@RequestMapping(value = "/StartTest", method = RequestMethod.POST)
	public String startTest(@ModelAttribute("testview") TestViews testView, Principal principal) {
		if (testView.getId() == 0) return "redirect:/SelectTest";
		myTestView = testViewsService.findById(testView.getId());
		System.out.println("Selected Test: " + testView.getId() + "gebruiker:" + principal.getName());
		// myTestsList = myTestView.getsortedTestViewsList();

		// test output start
		// for (TestViewsContent t: myTestsList){
		// System.out.println("Tests: " + t.getOrderNr() + " " +
		// t.getQuestionId());
		// }
		// System.out.println("First Question: " +
		// myTestView.getQuestionNr(1).getQuestionId());
		// test output end

		myTestView.startTest();
		return "redirect:/loadExamQuestion";
	}

	// Load Next Question
	// *********************************************************************

	@RequestMapping(value = "/LoadNextQuestion", method = RequestMethod.POST)
	public String loadNextQuestion(@ModelAttribute TestAnswerForm testAnswerForm, Principal principal) {
		myTestResults.setTestResults(new Integer(myTestView.getCurrentQuestion().getQuestionId()),
				testAnswerForm.getTestAnswers());
		myTestView.getNextQuestion();
		myTestResults.printValues();
        User user = iUserService.findByName(principal.getName());
        myTestResults.setUser(user);
        myTestResults.setExamId(myTestView.getId());

		System.out.println(myTestResults);
        // this doesn't work if no catch: nested exception is org.hibernate.PropertyAccessException: could not get a field value by reflection
//       try {

//        }catch (Exception e){
//            System.out.println("file not saved");
//        }
        System.out.println("should be saved by now");
		return "redirect:/loadExamQuestion";
	}

	// Load Previous Question
	// *********************************************************************

	@RequestMapping(value = "/LoadPrevQuestion", method = RequestMethod.POST)
	public String loadPrevQuestion(@ModelAttribute TestAnswerForm testAnswerForm, Principal principal) {
		myTestResults.setTestResults(new Integer(myTestView.getCurrentQuestion().getQuestionId()),
				testAnswerForm.getTestAnswers());
		myTestView.getPrevQuestion();
		myTestResults.printValues();
		if (testAnswerForm.getTestAnswers() != null) {
			for (int s : testAnswerForm.getTestAnswers()) {
				System.out.println("Answer = " + s + "gebruiker:" + principal.getName());
			}
		}
		System.out.println(myTestResults);

        return "redirect:/loadExamQuestion";
	}
	
	// Show All Questons in one overview
	// *********************************************************************

	@RequestMapping(value = "/ShowAllQuestions", method = RequestMethod.POST)
	public String showAllQuestions(Model model, @ModelAttribute TestAnswerForm testAnswerForm) {
		myTestResults.setTestResults(new Integer(myTestView.getCurrentQuestion().getQuestionId()),
				testAnswerForm.getTestAnswers());
		myTestResults.printValues();
		// Implement overview of all questions
		model.addAttribute("mytestview", myTestView);
		model.addAttribute("questionservice", iQuestionService);
		return "ShowAllQuestions";
	}
	
	
	// Stop the Test
	// *********************************************************************

	@RequestMapping(value = "/StopTheTest", method = RequestMethod.POST)
	public String stopTheTest(@ModelAttribute TestAnswerForm testAnswerForm) {
		myTestResults.setTestResults(new Integer(myTestView.getCurrentQuestion().getQuestionId()),
				testAnswerForm.getTestAnswers());
		myTestResults.printValues();
		return "StopTheTest";
	}

	// Evaluate the Test
	// *********************************************************************

	@RequestMapping(value = "/TestEvaluation", method = RequestMethod.POST)
	public String testEvaluation( Model model, Principal principal) {
		iTestResultService.saveTestResult(myTestResults);
		User user = iUserService.findByName(principal.getName());
		int i=0;
		List<TestResults> testResultses= (List<TestResults>) iTestResultService.findByUser(user);
		List<Question> questions= new ArrayList<Question>();
		TestResults lastTestResult = testResultses.get(testResultses.size()-1);
		System.out.println(lastTestResult.getTestResultId() );
		Map<Integer, List<Integer>> testResults = lastTestResult.getTestResults();
		int correctQuestions = 0;
		int incorrectQuestions = 0;
		for(Map.Entry<Integer, List<Integer>> element : testResults.entrySet()) {
			int vraagId = element.getKey();
			Question q = this.iQuestionService.findById(vraagId);
			questions.add(q);
			List<Integer> answers = element.getValue();
			boolean isOK = true;
			for(int a :answers){
				Answer answer = iAnswerService.findOne(a);
				isOK = isOK && answer.isCorrect();

			}
			if(isOK) {
				q.setCorrect(true);
				questions.add(q);
				correctQuestions++;
			}
			else {
				incorrectQuestions++;
			}

		}
		System.out.println(correctQuestions);
		System.out.println(incorrectQuestions);
		int score =(100/(correctQuestions+incorrectQuestions))*correctQuestions;
		model.addAttribute("questions", questions);
		model.addAttribute("score", score);
		// Implement overview of all questions
		return "TestEvaluation";
	}
	
	//display question after exam
	@RequestMapping(value = {"/displayQuestInfo/{id}"}, method = RequestMethod.GET)
	public String displayQuestion(@PathVariable("id") Integer  id,Model model) {
		System.out.println("we have id"+id);
		model.addAttribute("question", iQuestionService.findById(id));

		return "displayQuestion";
	}
}

class TestSelection {
	int selectedTest;

	@Override
	public String toString() {
		return "TestSelection{" + "selectedTest=" + selectedTest + '}';
	}
}
