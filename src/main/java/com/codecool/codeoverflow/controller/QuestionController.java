package com.codecool.codeoverflow.controller;


import com.codecool.codeoverflow.entity.Question;
import com.codecool.codeoverflow.service.QuestionService;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
