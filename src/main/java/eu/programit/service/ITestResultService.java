package eu.programit.service;

import eu.programit.domain.TestResults;
import eu.programit.domain.User;

public interface ITestResultService {
	    TestResults saveTestResult(TestResults testResult);
	    Iterable<TestResults> findAll();
	 //   Iterable<Answer> findAllByQuestion(Question question);
	    TestResults findById(int examID);
		Iterable<TestResults> findByUser(User user);
	}


