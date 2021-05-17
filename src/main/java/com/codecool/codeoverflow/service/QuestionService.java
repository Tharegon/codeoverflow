package com.codecool.codeoverflow.service;


import com.codecool.codeoverflow.entity.Question;
import com.codecool.codeoverflow.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    final
    QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestion(){
        return questionRepository.findAll();
    }
}
