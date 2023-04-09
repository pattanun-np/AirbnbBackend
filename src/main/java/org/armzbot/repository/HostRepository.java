package org.armzbot.repository;

import org.armzbot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT * FROM airbnb.host WHERE is_active = true")
    List<User> findAllHosts();
}
