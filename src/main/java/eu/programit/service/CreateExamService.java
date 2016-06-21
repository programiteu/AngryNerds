
package eu.programit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.programit.domain.Exam;
import eu.programit.domain.Question;
import eu.programit.repository.ExamRepository;

@Service
@Transactional
public class CreateExamService implements ICreateExamService {

	@Autowired
	private ExamRepository examRepository;

	@Override
	public Exam saveExam(Exam exam) {
		return examRepository.save(exam);
	}

	@Override
	public Iterable<Exam> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveQuestion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveQuestion(Question question) {
		// TODO Auto-generated method stub
		
	}

}
