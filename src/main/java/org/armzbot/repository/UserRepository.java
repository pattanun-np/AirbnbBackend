package org.armzbot.repository;

import org.armzbot.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);


    boolean existsByEmail(String email);


}
