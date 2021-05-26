package com.codecool.codeoverflow.service;


import com.codecool.codeoverflow.entity.Question;
import com.codecool.codeoverflow.model.QuestionCredentials;
import com.codecool.codeoverflow.repository.QuestionRepository;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService {
    final
    QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestion() {
        return questionRepository.findAll();
    }

    public ResponseEntity saveQuestion(QuestionCredentials questionCredentials) {
        if(!questionCredentials.getQuestionTitle().isBlank() &&
                !questionCredentials.getQuestionContent().isBlank()) {
            Question question = Question.builder()
                    .questionTitle(questionCredentials.getQuestionTitle())
                    .questionContent(questionCredentials.getQuestionContent())
                    .sendTime(LocalDateTime.now())
                    .build();
            questionRepository.save(question);
            Map<Object, Object> model = new HashMap<>();
            model.put("Successful question with: ", question.getQuestionTitle());
            return ResponseEntity.ok(model);
        }else{
            throw new IllegalArgumentException("Invalid title/content supplied, cannot be empty!");
        }
    }
}
