package eu.programit.service;

import eu.programit.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.programit.domain.TestResults;
import eu.programit.repository.TestResultRepository;
@Service
public class TestResultService implements ITestResultService {
    @Autowired
    TestResultRepository testResultRepository;

    @Override
    public TestResults saveTestResult(TestResults testResult) {
        return testResultRepository.save(testResult);
    }
//    @Override
//	public TestResults findById(int examID) {
//		// TODO Auto-generated method stub
//		return testResultRepository.findOne(examID);
//	}
//	@Override
//	public Iterable<TestResults> findAll() {
//		Iterable<TestResults> result = this.testResultRepository.findAll();
//		
//		return result;
//	}

	@Override
	public Iterable<TestResults> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestResults findById(int examID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<TestResults> findByUser(User user) {
		return testResultRepository.findByUser(user);
	}


//    @Override
//    public Iterable<Answer> findAllByQuestion(Question question) {
//        Iterable<Answer> itr = answerRepository.findAllByQuestion(question);
//
//        return itr ;
//    }
}
