package eu.programit.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
public class TestViews implements Serializable {

	private static final long serialVersionUID = 6392649144473391793L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String description;
	private String testType;			// Exam, subject, ...
	
	@OneToMany (mappedBy = "testView")
	private List<TestViewsContent> testViewsList;

	/****** Local Vars        ******************************************************/

	@Transient
	private int questionNr;
	
	@Transient
	private List<TestViewsContent> sortedTestViewsList;

	/****** Methods           ******************************************************/
	
	public void startTest(){
		// sort questions on order nr
		sortedTestViewsList = new ArrayList<>(testViewsList);
		sortedTestViewsList.sort(new Comparator<TestViewsContent>() {
			public int compare(TestViewsContent a, TestViewsContent b){
				return Integer.compare(a.orderNr, b.orderNr);
			}
		});
		// start with question nr 1 (questionNr is index thus starts with 0)
		questionNr = 0;
	}

	public TestViewsContent getNextQuestion(){
		if (questionNr <sortedTestViewsList.size()-1) questionNr++; // no next question if already at last
		System.out.println("getNextQuestion - sortedTestViewsList.size: " + sortedTestViewsList.size());
		System.out.println("getNextQuestion - sortedTestViewsListIndex: " + questionNr);
		return sortedTestViewsList.get(questionNr);
	}

	public TestViewsContent getPrevQuestion(){
		if (questionNr > 0) questionNr--; // no previous question if already at 1
		return sortedTestViewsList.get(questionNr);
	}

	// Set current question to index and return its value in table sortedTestViewsList 
//	public TestViewsContent getQuestionNr(int index) {
//		if (index >= 0 && index < sortedTestViewsList.size()) questionNr = index;
//		else questionNr = 0;
//		return sortedTestViewsList.get(questionNr);
//	}

	public TestViewsContent getCurrentQuestion() {
		return sortedTestViewsList.get(questionNr);	
	}
	
	public int getNrOfQuestions() {
		return sortedTestViewsList.size();
	}

	/****** Getters & Setters ******************************************************/
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<TestViewsContent> getTestViewsList() {
		return testViewsList;
	}

	public int getQuestionNr() {
		return questionNr;
	}

	public void setQuestionNr(int questionNr) {
		this.questionNr = questionNr;
	}

	public List<TestViewsContent> getSortedTestViewsList() {
		return sortedTestViewsList;
	}
	
	
}
