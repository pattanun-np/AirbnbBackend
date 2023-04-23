package org.armzbot.repository;

import lombok.NonNull;
import org.armzbot.entity.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends CrudRepository<Reservation, String> {


    public static final String FIND_BY_ID = """
            SELECT * FROM reservations
            WHERE reserve_id = ?1 AND is_active = true
            """;

    public static final String FIND_BY_USER_ID = """
                        
            SELECT * FROM reservations
            WHERE user_id = ?1 AND is_active = true
            """;

    public static final String FIND_BY_CHECK_IN = """
            SELECT * FROM reservations
            WHERE check_in = ?1 AND is_active = true
            """;


    public static final String FIND_BY_CHECK_IN_AND_CHECK_OUT = """
            SELECT * FROM reservations
            WHERE check_in = ?1 AND check_out = ?2 AND is_active = true
            """;

    public static final String FIND_BY_CHECK_OUT = """ 
            SELECT * FROM reservations
            WHERE check_out = ?1 AND is_active = true""";

    public static final String FIND_BY_PAYMENT_STATUS = """
            SELECT *  FROM reservations
            WHERE payment_status = ? AND is_active = true
            """;

    public static final String FIND_BY_ACCOMMODATION_ID = """
            SELECT * FROM reservations
            WHERE acc_id = ?1 AND is_active = true
            """;


    @Query(value = FIND_BY_ID, nativeQuery = true)
    Optional<Reservation> findByID(String reserve_id);


    @Query(value = FIND_BY_USER_ID, nativeQuery = true)
    List<Reservation> findByUserId(String user_id);

    @Query(value = FIND_BY_CHECK_IN, nativeQuery = true)
    List<Reservation> findByCheckIn(String check_in);


    @Query(value = FIND_BY_CHECK_OUT, nativeQuery = true)
    List<Reservation> findByCheckOut(String check_out);


    @Query(value = FIND_BY_PAYMENT_STATUS, nativeQuery = true)
    List<Reservation> findByPaymentStatus(String payment_status);


    @Query(value = FIND_BY_CHECK_IN_AND_CHECK_OUT, nativeQuery = true)
    List<Reservation> findByCheckInAndCheckOut(Date check_in, Date check_out);

    @Query(value = FIND_BY_ACCOMMODATION_ID, nativeQuery = true)
    List<Reservation> findByAccommodationId(String acc_id);

    @NonNull List<Reservation> findAll();


    @Query(value = FIND_BY_ACCOMMODATION_ID, nativeQuery = true)
    List<Reservation> findByAccommodationID(String acc_id);

}
