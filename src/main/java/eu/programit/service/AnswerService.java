package eu.programit.service;

import eu.programit.domain.Answer;
import eu.programit.domain.Question;
import eu.programit.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService implements IAnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Override
    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Iterable<Answer> findAllByQuestion(Question question) {
        Iterable<Answer> itr = answerRepository.findAllByQuestion(question);

        return itr ;
    }

    public Answer findOne(int id){
        return answerRepository.findOne(id);
    }
}
