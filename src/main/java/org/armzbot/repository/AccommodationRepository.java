package org.armzbot.repository;


import lombok.NonNull;
import org.armzbot.domain.common.query.SearchSpecification;
import org.armzbot.dto.AccommodationObject;
import org.armzbot.entity.Accommodation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccommodationRepository extends JpaRepository<Accommodation, String> {

    public static final String FIND_BY_LAT_LONG = "SELECT * FROM accommodations WHERE lat = ?1 AND lon = ?2";

    public static final String FIND_BY_ACCOMMODATION_NAME = "SELECT * FROM accommodations WHERE accommodation_name = ?1";

    public static final String FIND_BY_USER_ID = "SELECT * FROM accommodations WHERE user_id = ?1";

    public static final String FIND_ALL_WITH_LIMIT = "SELECT * FROM accommodations LIMIT ?1 OFFSET ?2";

    public static final String FIND_BY_ACC_ID = "SELECT * FROM accommodations WHERE id = ?1";

    public static final String FIND_ALL = "SELECT * FROM accommodations";

    @Query(value = FIND_BY_ACCOMMODATION_NAME, nativeQuery = true)
    Iterable<Accommodation> findByAccommodationName(String accommodationName);

    @Query(value = FIND_BY_LAT_LONG, nativeQuery = true)
    Optional<Accommodation> findByLatLong(double lat, double lon);

    @Query(value = FIND_BY_USER_ID, nativeQuery = true)
    List<Accommodation> findByUserId(String user_id);

    @NonNull List<Accommodation> findAll();


    @Query(value = FIND_BY_ACC_ID, nativeQuery = true)
    Optional<Accommodation> findByID(String id);

    @Query(value = FIND_ALL, nativeQuery = true)
    Page<Accommodation> findAll(SearchSpecification<AccommodationObject> searchSpecification, Pageable pageable);
}
