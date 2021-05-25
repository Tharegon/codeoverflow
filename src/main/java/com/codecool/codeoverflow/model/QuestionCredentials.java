package com.codecool.codeoverflow.model;


import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionCredentials {

    private String questionTitle;
    private String questionContent;
}
