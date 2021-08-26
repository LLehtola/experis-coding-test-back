package com.experis.experiscodingtestback.services;

import com.experis.experiscodingtestback.models.Question;
import com.experis.experiscodingtestback.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public boolean questionExists(long id) {
        return questionRepository.existsById(id);
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getQuestionsByAnswerId(long id) {
        return questionRepository.findAllByAnswersId(id);
    }
}
