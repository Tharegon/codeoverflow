package com.codecool.codeoverflow.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentCredentialsRegister {
    private String userName;
    private String email;
    private String password;
}
