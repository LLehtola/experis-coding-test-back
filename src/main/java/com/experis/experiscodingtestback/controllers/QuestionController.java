package com.experis.experiscodingtestback.controllers;


import com.experis.experiscodingtestback.models.Question;
import com.experis.experiscodingtestback.models.User;
import com.experis.experiscodingtestback.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ControllerHelpers.API_V1 + "/questions")

public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/additional")
    public ResponseEntity<List<Question>> getGeneralQuestions() {
        List <Question> generalQuestions = questionService.getGeneralQuestions();
        return new ResponseEntity<>(generalQuestions, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<List<Question>> getTestQuestions() {
        System.out.println("test");
        List<Question> questions = questionService.getTestQuestions();
        for (Question question: questions) {
            System.out.println(question.getId());
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question addedQuestion = questionService.createQuestion(question);
        return new ResponseEntity<>(addedQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(
            @PathVariable long id, @RequestBody Question newQuestion) {
        if (id != newQuestion.getId()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        boolean questionFound = questionService.questionExists(id);
        Question returnQuestion = questionService.saveQuestion(newQuestion);
        HttpStatus status;
        if (questionFound) {
            status = HttpStatus.NO_CONTENT;
        } else {
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(returnQuestion, status);
    }

    @PutMapping("/hide/{id}")
    public ResponseEntity<Question> hideOrShowQuestion(@PathVariable long id, @RequestBody Question question) {
        if (id != question.getId()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Question returnQuestion = null;
        boolean questionFound = questionService.questionExists(id);
        if (questionFound) {
            returnQuestion = questionService.hideOrShowHidden(id, question);

        }
        return new ResponseEntity<>(returnQuestion, HttpStatus.OK);
    }

    @GetMapping("/answer/{id}")
    public ResponseEntity<List<Question>> getQuestionsByAnswerId(@PathVariable long id) {
        List<Question> questions = questionService.getQuestionsByAnswerId(id);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

}
