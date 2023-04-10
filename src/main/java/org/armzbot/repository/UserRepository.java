package org.armzbot.repository;

import org.armzbot.models.User;

import java.util.List;

public interface UserRepository {

    User save(User user);


    User findById(String id);


    User findByEmail(String email);

    List<User> findAll();

    void delete(User user);


}
