package com.codecool.codeoverflow.service;

import com.codecool.codeoverflow.entity.Student;
import com.codecool.codeoverflow.model.StudentCredentialsRegister;
import com.codecool.codeoverflow.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class StudentApiService {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private StudentRepository studentRepository;

    public StudentApiService() {
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public ResponseEntity registerStudent(StudentCredentialsRegister studentCredentialsRegister) {
        Optional<Student> student = studentRepository.findByEmail(studentCredentialsRegister.getEmail());
        if (student.isEmpty()) {
            Student newStudent = Student
                    .builder()
                    .email(studentCredentialsRegister.getEmail())
                    .userName(studentCredentialsRegister.getUserName())
                    .password(passwordEncoder.encode(studentCredentialsRegister.getPassword()))
//                    .registrationDate(LocalDateTime.now())
//                    .roles(Arrays.asList("ROLE_USER"))
                    .build();
            studentRepository.save(newStudent);
            Map<Object, Object> model = new HashMap<>();
            model.put("Signed up with: ", studentCredentialsRegister.getEmail());
            return ResponseEntity.ok(model);
        } else {
            throw new BadCredentialsException("Email is already in use!");
        }
    }
}
