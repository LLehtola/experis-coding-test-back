package com.experis.experiscodingtestback.controllers;

import com.experis.experiscodingtestback.models.Question;
import com.experis.experiscodingtestback.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ControllerHelpers.API_V1 + "/questions")
@CrossOrigin

public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping()
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question addedQuestion = questionService.createQuestion(question);
        return new ResponseEntity<>(addedQuestion, HttpStatus.CREATED);
    }
}
