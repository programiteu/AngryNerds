package eu.programit.service;

import java.util.List;

import eu.programit.domain.Exam;
import eu.programit.domain.Question;
import eu.programit.repository.ExamRepository;

public interface ICreateExamService {

	public Exam saveExam(Exam exam) ;

	public Iterable<Exam> findAll(); 

	public void saveQuestion();

}
