package org.armzbot.controller;

import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import org.armzbot.adaptor.AuthAdaptor;
import org.armzbot.dto.LoginRequest;
import org.armzbot.dto.LoginResponse;
import org.armzbot.dto.RegisterRequest;
import org.armzbot.dto.RegisterResponse;
import org.armzbot.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthAdaptor authAdaptor;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest r) throws BaseException {
        RegisterResponse response = authAdaptor.register(r);

        response.setMessage("User registered successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest r) throws BaseException, FirebaseAuthException {
        LoginResponse response = authAdaptor.login(r);

        response.setMessage("User logged in successfully");
        return ResponseEntity.ok(response);
    }



}
