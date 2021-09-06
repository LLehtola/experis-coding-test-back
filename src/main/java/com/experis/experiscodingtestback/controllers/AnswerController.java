package com.experis.experiscodingtestback.controllers;

import com.experis.experiscodingtestback.models.Answer;
import com.experis.experiscodingtestback.models.Question;
import com.experis.experiscodingtestback.models.User;
import com.experis.experiscodingtestback.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ControllerHelpers.API_V1 + "/answers")

public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping
    public ResponseEntity<List<Answer>> getAnswers() {
        List<Answer> answers = answerService.getAnswers();
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Object>> getAnswersByUserId(@PathVariable long userId) {
        List<Object> response = answerService.getAnswersByUserId(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Integer> addAnswers(@RequestBody List<Answer> answers, @PathVariable long userId) {
        int result = answerService.addAnswers(answers, userId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
