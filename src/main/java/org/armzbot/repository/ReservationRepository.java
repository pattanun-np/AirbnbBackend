package org.armzbot.repository;

import org.armzbot.entity.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends CrudRepository<Reservation, String> {

    public static final String FIND_BY_BOOKING_ID = "SELECT r FROM reservations r WHERE r.booking_id = ?1";

    public static final String FIND_BY_USER_ID = "SELECT r FROM reservations r WHERE r.user_id = ?1";

    public static final String FIND_BY_CHECK_IN = "SELECT r FROM reservations r WHERE r.check_in = ?1";

    public static final String FIND_BY_CHECK_IN_AND_CHECK_OUT = "SELECT r FROM reservations r WHERE r.check_in = ?1 AND r.check_out = ?2";


    public static final String FIND_BY_CHECK_OUT = "SELECT r FROM reservations r WHERE r.check_out = ?1";


    public static final String FIND_BY_PAYMENT_STATUS = "SELECT r FROM reservations r WHERE r.payment_status = ?";


    @Query(value = FIND_BY_BOOKING_ID, nativeQuery = true)
    Optional<Reservation> findByBookingId(String booking_id);

    @Query(value = FIND_BY_USER_ID, nativeQuery = true)
    List<Reservation> findByUserId(String user_id);

    @Query(value = FIND_BY_CHECK_IN, nativeQuery = true)
    List<Reservation> findByCheckIn(String check_in);


    @Query(value = FIND_BY_CHECK_OUT, nativeQuery = true)
    List<Reservation> findByCheckOut(String check_out);


    @Query(value = FIND_BY_PAYMENT_STATUS, nativeQuery = true)
    List<Reservation> findByPaymentStatus(String payment_status);


    @Query(value = FIND_BY_CHECK_IN_AND_CHECK_OUT, nativeQuery = true)
    List<Reservation> findByCheckInAndCheckOut(String check_in, String check_out);


}
