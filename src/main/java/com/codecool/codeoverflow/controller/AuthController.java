package com.codecool.codeoverflow.controller;

import com.codecool.codeoverflow.model.StudentCredentialsRegister;
import com.codecool.codeoverflow.security.JwtTokenServices;
import com.codecool.codeoverflow.service.StudentApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {

    final
    StudentApiService studentApiService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenServices jwtTokenServices;


    public AuthController(StudentApiService studentApiService, AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices) {
        this.studentApiService = studentApiService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/auth/register")
    @ResponseBody
    public ResponseEntity register(@RequestBody StudentCredentialsRegister studentCredentialsRegister) {
        return studentApiService.registerStudent(studentCredentialsRegister);
    }
}
