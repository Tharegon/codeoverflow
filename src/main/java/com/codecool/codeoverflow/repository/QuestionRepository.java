package com.codecool.codeoverflow.repository;

import com.codecool.codeoverflow.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
