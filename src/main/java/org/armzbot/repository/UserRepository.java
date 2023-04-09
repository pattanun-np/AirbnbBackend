package org.armzbot.repository;

import org.armzbot.models.User;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT * FROM user WHERE user.is_active=true")
    Optional<User> findAllUsers();

    @Query(value = "SELECT * FROM user WHERE user.is_active=true AND user.user_id=:id")
    Optional<User> findUserById(@Param("id") String user_id);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
