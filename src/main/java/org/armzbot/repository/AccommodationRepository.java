package org.armzbot.repository;

import org.armzbot.entity.Accommodation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccommodationRepository extends CrudRepository<Accommodation, String> {

    public static final String FIND_BY_LAT_LONG = "SELECT * FROM accommodations WHERE lat = ?1 AND lon = ?2";

    public static final String FIND_BY_ACCOMMODATION_NAME = "SELECT * FROM accommodations WHERE accommodation_name = ?1";

    public static final String FIND_BY_USER_ID = "SELECT * FROM accommodations WHERE user_id = ?1";

    public static final String FIND_ALL_WITH_LIMIT = "SELECT * FROM accommodations LIMIT ?1 OFFSET ?2";

    public static final String FIND_BY_ACC_ID = "SELECT * FROM accommodations WHERE id = ?1";


    @Query(value = FIND_BY_ACCOMMODATION_NAME, nativeQuery = true)
    Iterable<Accommodation> findByAccommodationName(String accommodationName);


    @Query(value = FIND_BY_LAT_LONG, nativeQuery = true)
    Optional<Accommodation> findByLatLong(double lat, double lon);

    @Query(value = FIND_BY_USER_ID, nativeQuery = true)
    List<Accommodation> findByUserId(String user_id);

    Page<Accommodation> findAll(Pageable pageable);


    @Query(value = FIND_BY_ACC_ID, nativeQuery = true)
    Optional<Accommodation> findByID(String id);
}
