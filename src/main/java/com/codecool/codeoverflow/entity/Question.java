package com.codecool.codeoverflow.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Question {

    @Id
    @GeneratedValue
    private Long questionId;
    @Column(nullable = false)
    private String questionTitle;
    @Column(nullable = false)
    private String questionContent;
    private LocalDateTime sendTime;
}
