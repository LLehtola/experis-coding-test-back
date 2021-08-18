package com.experis.experiscodingtestback.controllers;

import com.experis.experiscodingtestback.models.Answer;
import com.experis.experiscodingtestback.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ControllerHelpers.API_V1 + "/answers")
@CrossOrigin

public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping()
    public ResponseEntity<List<Answer>> getAnswers() {
        List<Answer> answers = answerService.getAnswers();
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Integer> addAnswers(@RequestBody List<Answer> answers) {
        int result = answerService.addAnswers(answers);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
