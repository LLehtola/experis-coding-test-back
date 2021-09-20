package com.experis.experiscodingtestback.services;

import com.experis.experiscodingtestback.models.Question;
import com.experis.experiscodingtestback.models.QuestionCategory;
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

    //Finds all questions
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }



    /* Finds all questions by categories to separate lists
    * Checks the type of code questions and shuffles answer options if there is any
    * Shuffles all code questions and adds them to questions list
    * Returns a list which contains at first personal questions and after them shuffled code questions
    */
    public List<Question> getTestQuestions() {
        List <Question> questions = questionRepository.findAllByCategory(QuestionCategory.GENERAL.ordinal());
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

    public List<Question> getGeneralQuestions() {
        List <Question> generalQuestions = questionRepository.findAllByCategory(QuestionCategory.ADDITIONAL.ordinal());
        return generalQuestions;
    }

    /* Finds question by id
    * Sets the value of the hidden from false to true and and vice versa
    * Return the updated question
    */
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
