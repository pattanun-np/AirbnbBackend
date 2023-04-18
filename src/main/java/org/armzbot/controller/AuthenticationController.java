package org.armzbot.controller;

import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import org.armzbot.adaptor.AuthAdaptor;
import org.armzbot.dto.*;
import org.armzbot.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthAdaptor authAdaptor;

    ///api/v1/auth/register
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest r) throws BaseException {
        RegisterResponse response = authAdaptor.register(r);
        response.setMessage("User registered successfully");
        return ResponseEntity.ok(response);
    }

    ///api/v1/auth/login
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest r) throws BaseException, FirebaseAuthException {
        LoginResponse response = authAdaptor.login(r);
        response.setMessage("User logged in successfully");
        return ResponseEntity.ok(response);
    }


    @PostMapping("/change-password")
    public ResponseEntity<LoginResponse> changePassword(@RequestBody LoginRequest r) throws BaseException, FirebaseAuthException {
        LoginResponse response = authAdaptor.login(r);
        response.setMessage("User logged in successfully");
        return ResponseEntity.ok(response);
    }

    ///api/v1/auth/login-google
    @GetMapping("/login-google")
    public ResponseEntity<LoginResponse> loginGoogle(@RequestParam(name = "id_token", value = "id_token", required = false) String id_token) throws BaseException, FirebaseAuthException {
        if (id_token == null) {
            throw new BaseException("id_token is null", 400);
        }
        LoginResponse response = authAdaptor.loginGoogle(id_token);
        response.setMessage("User logged in successfully");
        return ResponseEntity.ok(response);
    }
    ///api/v1/auth/is-user-exist

    @GetMapping("/is-user-exist")
    public ResponseEntity<Boolean> isUserExist(@RequestParam(name = "id_token", value = "id_token", required = false) String id_token) throws FirebaseAuthException, BaseException {

        if (id_token == null) {
            throw new BaseException("id_token is null", 400);
        }
        boolean res = authAdaptor.isUserExist(id_token);
        if (res) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }

    }

    ///api/v1/auth/logout
    @GetMapping("/logout")
    public ResponseEntity<LogoutResponse> logout() throws BaseException, FirebaseAuthException {
        LogoutResponse response = new LogoutResponse();
        response.setMessage("User logged out successfully");
        return ResponseEntity.ok(response);
    }

}
