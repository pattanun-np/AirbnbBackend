package org.armzbot.services;

import lombok.extern.log4j.Log4j2;
import org.armzbot.entity.User;
import org.armzbot.exception.UserException;
import org.armzbot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;


    }

    //    @Cacheable(value = "user", key = "#id", unless = "#result == null")
    public Optional<User> findById(String id) {
        //log.info("Load User from DB: " + id);
        return userRepository.findById(id);
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

    // Password Matcher
//    public boolean matchPassword(String queryPassword, String encodedPassword) {
//        return passwordEncoder.matches(queryPassword, encodedPassword);
//
//    }

    //    @CachePut(value = "user", key = "#id")
    public User updateName(String id, String name) {

        Optional<User> opt = userRepository.findById(id);
        if (opt.isEmpty()) {
            throw UserException.NotFound();
        }
        User user = opt.get();
        return userRepository.save(user);
    }

    public User create(
            String email,
            String password,
            String firstname,
            String lastname,
            String phone) {
        // Validate email
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw UserException.invalidEmail();
        }
        // Validate password
        if (password.length() < 8) {
            throw UserException.invalidPassword();
        }
        // Validate name
        if (firstname.length() < 2) {
            throw UserException.invalidName();
        }
        // Validate phone
        if (phone.length() < 10) {
            throw UserException.invalidPhone();
        }

        // Check if user already exists
        if (userRepository.existsByEmail(email)) {
            throw UserException.alreadyExists();
        }
        // Save
        User entity = new User();
        entity.setEmail(email);
        entity.setFirstname(firstname);
        entity.setLastname(lastname);
        entity.setPhone(phone);
//        entity.setPassword(passwordEncoder.encode(password));
        return userRepository.save(entity);

    }


}
