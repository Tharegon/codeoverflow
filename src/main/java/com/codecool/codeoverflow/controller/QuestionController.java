package com.codecool.codeoverflow.controller;


import com.codecool.codeoverflow.entity.Question;
import com.codecool.codeoverflow.model.QuestionCredentials;
import com.codecool.codeoverflow.model.StudentCredentialsRegister;
import com.codecool.codeoverflow.service.QuestionService;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {
    final
    QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @CrossOrigin(origins = "localhost/3000")
    @GetMapping("/questions")
    public List<Question> getAllQuestion(){
        return questionService.getAllQuestion();
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/send-question")
    @ResponseBody
    public ResponseEntity sendQuestion(@RequestBody QuestionCredentials questionCredentials) {
        return questionService.saveQuestion(questionCredentials);
    }
}
