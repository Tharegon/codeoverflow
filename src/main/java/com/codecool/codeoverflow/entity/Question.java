package com.codecool.codeoverflow.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Question {

    @Id
    private Long questionId;
    @Column(nullable = false)
    private String questionTitle;
    @Column(nullable = false)
    private String questionContent;
}
