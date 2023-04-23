package org.armzbot.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.armzbot.entity.Accommodation;
import org.armzbot.repository.AccommodationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    public List<Accommodation> getAllAccommodations() {
        ArrayList<Accommodation> accommodations = new ArrayList<>();
        int limit = 10;

        Iterable<Accommodation> result = accommodationRepository.findAll();

        result.forEach(acc -> {
            System.out.println(acc);
            accommodations.add(acc);
        });


        return accommodations;

    }



    public Optional<Accommodation> getAccommodationById(String acc_id) {
        return accommodationRepository.findByID(acc_id);
    }

    public void addAccommodation(Accommodation accommodation) {
        accommodationRepository.save(accommodation);
    }

    public void updateAccommodation(String acc_id, Accommodation accommodation) {
        Optional<Accommodation> repository = accommodationRepository.findByID(acc_id);
        if (repository.isEmpty()) {
            return;
        }
        Accommodation result = repository.get();
        accommodationRepository.save(result);
    }

    public void deleteAccommodationById(String acc_id) {

        Optional<Accommodation> repository = accommodationRepository.findByID(acc_id);
        if (repository.isEmpty()) {
            return;
        }

        Accommodation result = repository.get();
        result.set_active(false);
        accommodationRepository.save(result);
    }


}

