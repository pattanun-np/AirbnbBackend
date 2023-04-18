package org.armzbot.repository;

import org.armzbot.entity.AccommodationImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccommodationImageRepository extends JpaRepository<AccommodationImages, String> {

    public static final String FIND_BY_ACCOMMODATION_ID = "SELECT * FROM accommodation_images WHERE accommodation_id = ?1";

    public static final String FIND_BY_ID = "SELECT * FROM accommodation_images WHERE id = ?1";

    @Query(value = FIND_BY_ACCOMMODATION_ID, nativeQuery = true)
    List<AccommodationImages> findByAccommodationId(String accommodationId);

    @Query(value = FIND_BY_ID, nativeQuery = true)
    Optional<AccommodationImages> findByID(String id);
}
