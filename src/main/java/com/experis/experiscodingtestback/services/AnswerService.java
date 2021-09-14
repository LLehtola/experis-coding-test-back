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

import java.util.*;

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
        return answerRepository.findAllByUserId(id);
    }

    /* Saves answers from the list to the database
    * Sets a user for answers
    * Calls an another method which checks if the answer is correct and
    * adds points to result variable
    * Returns result as an integer
    */
    public int addAnswers(List<Answer> answers, long userId) {
        int result = 0;
        User user = userRepository.getById(userId);
        List<Answer> savedAnswers = new ArrayList<>();
        Date date = new Date();
        for (Answer answer: answers) {
            answer.setUser(user);
            answerRepository.save(answer);
            savedAnswers.add(answer);
            result += checkResult(answer);
        }
        user.setPoints(result);
        user.setAnswerdate(date);
        userRepository.save(user);
        return result;
    }

    //Checks if the user answer match for the correct answer of the question
    private int checkResult(Answer answer) {
        if (answer.getQuestion().getCorrectAnswer().equals(answer.getAnswer())) {
            return 10;
        }
        return 0;
    }

    /* Checks if the user exists by given user id
    * If not throws an exception
    * Finds all answers by given user id and questions for found answers
    * Creates wrapper objects which contain a collection of needed data from answers and questions
    * Returns list of wrapper objects
    */
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
            resultWrapper.setCode(question.getCode());
            userAnswers.add(resultWrapper);
        }
        return userAnswers;
    }
}
