package com.experis.experiscodingtestback.services;

import com.experis.experiscodingtestback.models.Question;
import com.experis.experiscodingtestback.models.QuestionCategory;
import com.experis.experiscodingtestback.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        List <Question> questions = questionRepository.findAllByCategory(QuestionCategory.PERSONAL.ordinal());
        List <Question> codeQuestions = questionRepository.findAllByCategory(QuestionCategory.CODE.ordinal());
        List <Question> shuffledQuestions = new ArrayList<>();
        for (Question question: codeQuestions) {
            if (question.getType() == MULTIPLECHOICE) {
                ArrayList<String> answerOptions = question.getAnswerOptions();
                Collections.shuffle(answerOptions);
                question.setAnswerOptions(answerOptions);
            }
            shuffledQuestions.add(question);
        }
        Collections.shuffle(shuffledQuestions);
        questions.addAll(shuffledQuestions);
        return questions;
    }

    public Question hideOrShowHidden(long id, Question question) {
        Question returnQuestion = questionRepository.findById(id).get();
        returnQuestion.setHidden(!question.isHidden());
        questionRepository.save(returnQuestion);
        return returnQuestion;
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
