package eu.programit.service;

import eu.programit.domain.Answer;
import eu.programit.domain.Question;

public interface IAnswerService {

    public Answer findOne(int id);
    Answer saveAnswer(Answer answer);
    Iterable<Answer> findAllByQuestion(Question question);
}
