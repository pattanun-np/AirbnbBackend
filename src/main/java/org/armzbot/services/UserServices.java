package org.armzbot.services;

import org.armzbot.models.User;
import org.armzbot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    private final UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllHosts() {
        return userRepository.findAllHosts();
    }

    public User getHostById(String id) throws IOException {
        if (id == null) {
            throw new IOException("input is null");
        }
        Optional<User> repository = userRepository.findById(id);
        if (!repository.isPresent()) {
            throw new IOException("not found");
        }
        User user = repository.get();
        if (!user.isActive()) {
            throw new IOException("not found");
        }
        return user;
    }

    public void addHost(@RequestBody User user) throws IOException {
        if (user.hasNull()) {
            throw new IOException("input is null");
        }
        if (userRepository.findById(user.getUser_id()).isPresent()
                && userRepository.findById(user.getUser_id()).get().isActive()) {
            throw new IOException("id has already been");
        }
        userRepository.save(user);
    }
    public void updateHostById(String id, String username,
                               String firstname, String lastname,
                               String email, String phone) throws IOException {

        if (id == null || (username == null && firstname == null
                && lastname == null && email == null && phone == null)) {
            throw new IOException("input is null");
        }

        Optional<User> repository = userRepository.findById(id);

        if (!repository.isPresent()) {
            throw new IOException("not found");
        }

        User user = repository.get();

        if (!user.isActive()) {
            throw new IOException("not found");
        }
        if (username != null) {
            user.setUsername(username);
        }
        if (firstname != null) {
            user.setFirstname(firstname);
        }
        if (lastname != null) {
            user.setLastname(lastname);
        }
        if (email != null) {
            user.setEmail(email);
        }
        if (phone != null) {
            user.setPhone(phone);
        }

        userRepository.save(user);
    }
    public void deleteHostById(String id) throws IOException {
        if (id == null) {
            throw new IOException();
        }
        Optional<User> repository = userRepository.findById(id);
        if (!repository.isPresent()) {
            throw new IOException("not found");
        }
        User user = repository.get();
        if (!user.isActive()) {
            throw new IOException("not found");
        }
        user.setActive(false);
        userRepository.save(user);
    }
}
