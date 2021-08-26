package com.experis.experiscodingtestback.services;

import com.experis.experiscodingtestback.models.Answer;
import com.experis.experiscodingtestback.models.Question;
import com.experis.experiscodingtestback.models.User;
import com.experis.experiscodingtestback.models.wrappers.ResultWrapper;
import com.experis.experiscodingtestback.repositories.AnswerRepository;
import com.experis.experiscodingtestback.repositories.QuestionRepository;
import com.experis.experiscodingtestback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }



    public List<Answer> getAnswersById(long id) {
        //User returnUser = userRepository.findById(id).get();
        return answerRepository.findAllByUserId(id);
    }
    /*
    public List<Answer> getAnswersById(String id) {
        return answerRepository.getAnswersById(id);
    }
*/

    public int addAnswers(List<Answer> answers, long userId) {
        int result = 0;
        User user = userRepository.getById(userId);
        List<Answer> savedAnswers = new ArrayList<>();
        for (Answer answer: answers) {
            answer.setUser(user);
            answerRepository.save(answer);
            savedAnswers.add(answer);
            result += checkResult(answer);
        }
        user.setPoints(result);
        userRepository.save(user);
        return result;
    }

    private int checkResult(Answer answer) {
        if (answer.getQuestion().getCorrectAnswer().equals(answer.getAnswer())) {
            return 10;
        }
        return 0;
    }

    public List<Object> getAnswersByUserId(long userId) {
        if(!userRepository.existsById(userId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        List<Object> userAnswers = new ArrayList<>();
        List<Answer> answers = answerRepository.findAllByUserId(userId);
        for (Answer answer: answers) {
            Question question = answer.getQuestion();
            ResultWrapper resultWrapper = new ResultWrapper();
            resultWrapper.setUserAnswer(answer.getAnswer());
            resultWrapper.setQuestion(question.getQuestion());
            resultWrapper.setCorrectAnswer(question.getCorrectAnswer());
            userAnswers.add(resultWrapper);
        }
        return userAnswers;
    }
}
