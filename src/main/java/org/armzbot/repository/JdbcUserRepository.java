package org.armzbot.repository;

import org.armzbot.dto.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public User save(@NotNull User user) {

        String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
        this.jdbcTemplate.update(sql, user.getEmail(),user.getPassword());
        return user;

    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void delete(org.armzbot.models.User user) {

    }

    @Override
    public void delete(User user) {

    }
}
