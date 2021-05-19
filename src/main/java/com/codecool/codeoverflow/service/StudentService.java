package com.codecool.codeoverflow.service;

import com.codecool.codeoverflow.entity.Student;
import com.codecool.codeoverflow.model.StudentCredentialsRegister;
import com.codecool.codeoverflow.repository.StudentRepository;
import com.codecool.codeoverflow.security.JwtTokenServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentService {
    private final PasswordEncoder passwordEncoder;

    private final StudentRepository studentRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenServices jwtTokenServices;


    public StudentService(StudentRepository studentRepository, AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices) {
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.studentRepository = studentRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
    }

    public ResponseEntity registerStudent(StudentCredentialsRegister studentCredentialsRegister) {
        Optional<Student> student = studentRepository.findByEmail(studentCredentialsRegister.getEmail());
        if (student.isEmpty()) {
            Student newStudent = Student
                    .builder()
                    .email(studentCredentialsRegister.getEmail())
                    .userName(studentCredentialsRegister.getUserName())
                    .password(passwordEncoder.encode(studentCredentialsRegister.getPassword()))
                    .registrationDate(LocalDateTime.now())
                    .roles(Arrays.asList("ROLE_USER"))
                    .build();
            studentRepository.save(newStudent);
            Map<Object, Object> model = new HashMap<>();
            model.put("Signed up with: ", studentCredentialsRegister.getEmail());
            return ResponseEntity.ok(model);
        } else {
            throw new BadCredentialsException("Email is already in use!");
        }
    }

    private void getHttpOnlyCookie(HttpServletResponse response, String token) {
        Cookie tokenCookie = new Cookie("token", token);
        int expiry = 7 * 24 * 60 * 60;
        tokenCookie.setMaxAge(expiry);
        tokenCookie.setSecure(false);
        tokenCookie.setHttpOnly(true);
        tokenCookie.setPath("/");
        response.addCookie(tokenCookie);
    }

    public ResponseEntity login(StudentCredentialsRegister loginData, HttpServletResponse response) {
        try {
            String userEmail = loginData.getEmail();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail, loginData.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            String userName = authentication.getName();
            String token = jwtTokenServices.createToken(userEmail, roles);
            getHttpOnlyCookie(response, token);
            Map<Object, Object> model = new HashMap<>();
            model.put("userEmail", userEmail);
            model.put("roles", roles);
            model.put("userName", userName);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid e-mail/password supplied");
        }
    }

    private void removeTokenFromResponse(HttpServletResponse response) {
        Cookie tokenCookie = new Cookie("token", null);
        tokenCookie.setMaxAge(0);
        tokenCookie.setHttpOnly(true);
        tokenCookie.setPath("/");
        response.addCookie(tokenCookie);
    }

    public ResponseEntity logOut(HttpServletResponse response, HttpServletRequest request) {
        try {
            removeTokenFromResponse(response);
            Map<Object, Object> model = new HashMap<>();
            model.put("Logout", "Successful");
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("You have not logged in.");
        }
    }
}
