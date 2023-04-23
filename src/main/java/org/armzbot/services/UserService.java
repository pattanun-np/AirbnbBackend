package org.armzbot.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.armzbot.entity.User;
import org.armzbot.exception.BaseException;
import org.armzbot.exception.UserException;
import org.armzbot.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    //    @Cacheable(value = "user", key = "#id", unless = "#result == null")
    public Optional<User> findById(String id) {
        //log.info("Load User from DB: " + id);
        return userRepository.findById(id);
    }

    // Find user by username
    public Optional<User> findByUsername(String username) {
        //log.info("Load User from DB by Username: " + username);
        return userRepository.findByUsername(username);
    }


    // Find user by email
    public Optional<User> findByEmail(String email) {
        //log.info("Load User from DB by Email: " + email);
        return userRepository.findByEmail(email);

    }


    // Update user
    public User update(User user) {
        return userRepository.save(user);
    }

    //Password Matcher
    public boolean matchPassword(String queryPassword, String encodedPassword) {
        return passwordEncoder.matches(queryPassword, encodedPassword);

    }

    //    @CachePut(value = "user", key = "#id")
    public User updateName(String id, String name) throws UserException {

        Optional<User> opt = userRepository.findById(id);
        if (opt.isEmpty()) {
            throw UserException.notFound();
        }
        User user = opt.get();
        return userRepository.save(user);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User create(User user) throws BaseException {

        System.out.println("Creating user: " + user.getUsername());
//        System.out.println(user);
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
        if (userRepository.existsByEmail(user.getEmail())) {
            throw UserException.alreadyExists();
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            throw UserException.alreadyExists();
        }

        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);

    }


}
