package com.codecool.codeoverflow.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Student {

    @Id
    @GeneratedValue
    private Long studentId;
    @Column(nullable = false,unique = true)
    private String userName;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
}
