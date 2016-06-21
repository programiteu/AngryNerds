package eu.programit.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import eu.programit.domain.Exam;

@Component
public interface ExamRepository extends CrudRepository<Exam,Integer> {
			
		//Do we need line 10
	    //Collection<Exam> findAllByExam(Exam id);
}
