package org.armzbot.adaptor;

import com.google.firebase.auth.FirebaseAuthException;
import lombok.extern.log4j.Log4j2;
import org.armzbot.dto.LoginRequest;
import org.armzbot.dto.LoginResponse;
import org.armzbot.dto.RegisterRequest;
import org.armzbot.dto.RegisterResponse;
import org.armzbot.entity.User;
import org.armzbot.exception.BaseException;
import org.armzbot.exception.UserException;
import org.armzbot.services.TokenService;
import org.armzbot.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class AuthAdaptor {
    private final UserService userService;
    public final TokenService tokenService;

    public AuthAdaptor(UserService userService,
                       TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    public LoginResponse login(LoginRequest r) throws BaseException, FirebaseAuthException {
        String username = r.getUsername();
        String password = r.getPassword();
//        System.out.println("Logging in user: " + username);
        Optional<User> optUser = userService.findByUsername(username);
        if (optUser.isEmpty()) {
            throw UserException.notFound();
        }
        User user = optUser.get();
        if (!userService.matchPassword(password, user.getPassword())) {
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

        user = userService.create(user);
        return new RegisterResponse();


    }


}
