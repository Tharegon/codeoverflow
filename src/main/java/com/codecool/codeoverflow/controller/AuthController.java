package com.codecool.codeoverflow.controller;

import com.codecool.codeoverflow.model.StudentCredentialsRegister;
import com.codecool.codeoverflow.model.error.ErrorInfo;
import com.codecool.codeoverflow.security.JwtTokenServices;
import com.codecool.codeoverflow.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class AuthController {

    final
    StudentService studentService;


    public AuthController(StudentService studentService) {
        this.studentService = studentService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/auth/register")
    @ResponseBody
    public ResponseEntity register(@RequestBody StudentCredentialsRegister studentCredentialsRegister) {
        return studentService.registerStudent(studentCredentialsRegister);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    //allowCredentials = "true" were here too, but it doesn't work right now
    @PostMapping("/auth/login")
    @ResponseBody
    public ResponseEntity login(@RequestBody StudentCredentialsRegister loginData, HttpServletResponse response) {
        return studentService.login(loginData, response);
    }

    //@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @GetMapping("/auth/logout")
    @ResponseBody
    public ResponseEntity logOut(HttpServletResponse response, HttpServletRequest request) {
        return studentService.logOut(response, request);
    }


    @ExceptionHandler(value = {BadCredentialsException.class,
            IllegalStateException.class, ResponseStatusException.class,
            HttpClientErrorException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    protected ErrorInfo handleConflict(RuntimeException e) {
        return new ErrorInfo(e);
    }

}
