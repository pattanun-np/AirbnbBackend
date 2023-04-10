package org.armzbot.services;

import org.armzbot.models.User;
import org.armzbot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServices {

    private final UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    public User getUserById(String id) throws IOException {
        if (id == null) {
            throw new IOException("input is null");
        }
        Optional<User> repository = userRepository.findUserById(id);
        if (!repository.isPresent()) {
            throw new IOException("Not found id : " + id);
        }
        return repository.get();
    }

    public void addUser(String username, String password, String firstname,
                        String lastname, String email, String phone) throws IOException {
        //null?
        if (Objects.isNull(username)) {
            throw new IOException("username is null");
        }
        if (Objects.isNull(password)) {
            throw new IOException("password is null");
        }
        if (Objects.isNull(firstname)) {
            throw new IOException("firstname is null");
        }
        if (Objects.isNull(lastname)) {
            throw new IOException("lastname is null");
        }
        if (Objects.isNull(email)) {
            throw new IOException("email is null");
        }
        if (Objects.isNull(phone)) {
            throw new IOException("phone is null");
        }
        //exist?
        Optional<User> repository = userRepository.findByUsername(username);
        User tmp;
        if (repository.isPresent()){
            tmp = repository.get();
            if(tmp.isActive()){
                throw new IOException("id has already been");
            }
        }
        else throw new IOException("id has already been");
        //save
        User entity = new User(username, /*encrypt*/ password, firstname, lastname, email, phone);
        userRepository.save(entity);
    }
    public void updateUserById(String id, String username,
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
    public void deleteUserById(String id) throws IOException {
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
        user.setActive(false);
        userRepository.save(user);
    }
}
