package com.experis.experiscodingtestback.services;

import com.experis.experiscodingtestback.models.Answer;
import com.experis.experiscodingtestback.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }

    public int addAnswers(List<Answer> answers) {
        int result = 0;
        List<Answer> savedAnswers = new ArrayList<>();
        for (Answer answer: answers) {
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
