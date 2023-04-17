package org.armzbot.adaptor;

import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.armzbot.dto.*;
import org.armzbot.entity.User;
import org.armzbot.exception.BaseException;
import org.armzbot.exception.UserException;
import org.armzbot.services.TokenService;
import org.armzbot.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class AuthAdaptor {
    private final UserService userService;
    public final TokenService tokenService;

    public final PasswordEncoder passwordEncoder;


    public LoginResponse login(LoginRequest r) throws BaseException, FirebaseAuthException {
        String username = r.getUsername();
        String password = r.getPassword();
//        System.out.println("Logging in user: " + username);
        Optional<User> optUser = userService.findByUsername(username);
        if (optUser.isEmpty()) {
            throw UserException.notFound();
        }
        User user = optUser.get();
        if (userService.matchPassword(password, user.getPassword())) {
            throw UserException.invalidPassword();
        }

        String token = tokenService.tokenize(user);
//        System.out.println("Token: " + token);
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        return response;
    }

    public RegisterResponse register(RegisterRequest r) throws BaseException {
        System.out.println("Registering user: " + r.getUsername());
        Optional<User> optUser = userService.findByEmail(r.getEmail());
        if (optUser.isPresent()) {
            throw UserException.alreadyExists();
        }
        // Use builder from lombok to create a new user object
        User user = User.builder()
                .firstname(r.getFirstname())
                .lastname(r.getLastname())
                .email(r.getEmail())
                .phone(r.getPhone())
                .username(r.getUsername())
                .password(r.getPassword())
                .provider("local")
                .build();


        // Validate email
        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw UserException.invalidEmail();
        }
        // Validate password
        if (user.getPassword().length() < 8) {
            throw UserException.invalidPasswordPattern();
        }
        // Validate name
        if (user.getFirstname().length() < 2) {
            throw UserException.invalidName();
        }
        // Validate phone
        if (user.getPhone().length() < 10) {
            throw UserException.invalidPhone();
        }

        // Check if user already exists
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            throw UserException.alreadyExists();
        }

        if (userService.findByUsername(user.getUsername()).isPresent()) {
            throw UserException.alreadyExists();
        }

        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));


        userService.create(user);
        return new RegisterResponse();


    }

    public ChangePasswordResponse changePassword(ChangePasswordRequest r) throws BaseException {
        String username = r.getUsername();
        String oldPassword = r.getOldPassword();
        String newPassword = r.getNewPassword();
        Optional<User> optUser = userService.findByUsername(username);
        if (optUser.isEmpty()) {
            throw UserException.notFound();
        }
        User user = optUser.get();
        if (userService.matchPassword(oldPassword, user.getPassword())) {
            throw UserException.invalidPassword();
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userService.update(user);
        return new ChangePasswordResponse();
    }

}
