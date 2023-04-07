package org.armzbot.repository;

import org.armzbot.models.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HostRepository extends JpaRepository<Host, String> {

    @Query(value = "SELECT * FROM airbnb.host WHERE is_active = true")
    List<Host> findAllHosts();
}
