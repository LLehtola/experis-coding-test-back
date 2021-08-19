package com.experis.experiscodingtestback.services;

import com.experis.experiscodingtestback.models.Answer;
import com.experis.experiscodingtestback.models.User;
import com.experis.experiscodingtestback.repositories.AnswerRepository;
import com.experis.experiscodingtestback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }

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
        return result;
    }

    private int checkResult(Answer answer) {
        if (answer.getQuestion().getCorrectAnswer().equals(answer.getAnswer())) {
            return 10;
        }
        return 0;
    }
}
