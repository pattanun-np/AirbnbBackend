package org.armzbot.repository;

import org.armzbot.entity.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends CrudRepository<Reservation, String> {

    public static final String FIND_BY_BOOKING_ID = "SELECT r FROM Reservation r WHERE r.booking_id = ?1";

    @Query(FIND_BY_BOOKING_ID)
    Optional<Reservation> findByBookingId(String booking_id);

    List<Reservation> findByUserId(String user_id);


}
