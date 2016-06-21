package eu.programit.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import eu.programit.domain.Exam;
import eu.programit.repository.ExamRepository;

public class ExamService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QuestionService.class);
	
	@Autowired
	private ExamRepository examRepository;

	
	public Exam save(Exam exam) {
		
		LOGGER.debug("it rocks");
		
		Exam result = this.examRepository.save(exam);	
		
		return result;

	}
	
	public Iterable<Exam> findAll() {
		Iterable<Exam> result = this.examRepository.findAll();
		
		return result;
	}

}
