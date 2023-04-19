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
    public void update(User user) {
        userRepository.save(user);
    }

    //Password Matcher
    public boolean matchPassword(String queryPassword, String encodedPassword) {
        return !passwordEncoder.matches(queryPassword, encodedPassword);

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


        return userRepository.save(user);

    }


    public void updateUserProfile(String userId, User user) throws UserException {
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            throw UserException.notFound();
        }
        User user1 = optUser.get();
        user1.setFirstname(user.getFirstname());
        user1.setLastname(user.getLastname());
        user1.setEmail(user.getEmail());
        user1.setPhone(user.getPhone());
        user1.setUsername(user.getUsername());
        userRepository.save(user1);
    }

}
