package com.experis.experiscodingtestback.services;

import com.experis.experiscodingtestback.models.Question;
import com.experis.experiscodingtestback.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.experis.experiscodingtestback.models.QuestionType.MULTIPLECHOICE;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getTestQuestions() {
        List <Question> questions = questionRepository.findAll();
        List <Question> shuffledQuestions = new ArrayList<>();
        for (Question question: questions) {
            if (question.getType() == MULTIPLECHOICE) {
                ArrayList<String> answerOptions = question.getAnswerOptions();
                Collections.shuffle(answerOptions);
                question.setCorrectAnswer("");
                question.setAnswerOptions(answerOptions);
            }
            shuffledQuestions.add(question);
        }
        Collections.shuffle(shuffledQuestions);
        return shuffledQuestions;
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
