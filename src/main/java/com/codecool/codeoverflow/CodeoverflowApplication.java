package com.codecool.codeoverflow;

import com.codecool.codeoverflow.entity.Question;
import com.codecool.codeoverflow.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class CodeoverflowApplication {

    public CodeoverflowApplication(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CodeoverflowApplication.class, args);
    }

    final QuestionRepository questionRepository;

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            Question first = new Question((long) 1,"Why lombok doesnt work?","very good explanation");

            questionRepository.save(first);

        };
    }
}