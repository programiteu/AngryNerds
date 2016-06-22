package eu.programit.repository;

import eu.programit.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import eu.programit.domain.TestResults;

@Component
public interface TestResultRepository extends CrudRepository<TestResults, Integer>{

	TestResults save(TestResults testResult);
	Iterable<TestResults> findByUser(User user);

}
