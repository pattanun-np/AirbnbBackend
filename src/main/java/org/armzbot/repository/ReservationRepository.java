package org.armzbot.repository;

import org.armzbot.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, String> {


    @Query(value = "SELECT * FROM reservation WHERE reservation.is_active=true")
    Optional<Reservation> findById(String reserve_id);

    @Query(value = "SELECT * FROM reservation WHERE reservation.is_active=true AND reservation.user_id=:id")
    List<Reservation> findByUserId(@Param("id") String user_id);

    void deleteByUserId(String user_id);
}
