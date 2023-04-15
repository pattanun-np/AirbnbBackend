package org.armzbot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccommodationRepository<Accommodation> extends CrudRepository<Accommodation, String> {
    Optional<Accommodation> findByID(String id);

    Optional<Accommodation> findByAddress(String address);



}
