package org.armzbot.repository;

import org.armzbot.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

    List<Reservation> findByUserId(String user_id);

    void deleteByUserId(String user_id);
}
