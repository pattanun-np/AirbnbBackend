package org.armzbot.controller;

import lombok.RequiredArgsConstructor;
import org.armzbot.adaptor.AccommodationAdaptor;
import org.armzbot.dto.AccommodationRequest;
import org.armzbot.dto.AccommodationResponse;
import org.armzbot.entity.Accommodation;
import org.armzbot.exception.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accommodation")
@RequiredArgsConstructor
public class AccommodationController {
    private final AccommodationAdaptor accommodationAdaptor;

    @GetMapping(path = "/{accommodation_id}")
    public Optional<Accommodation> getAccommodationById(@PathVariable(value = "accommodation_id") String accommodation_id) throws Exception {
        return accommodationAdaptor.getAccommodationById(accommodation_id);
    }

    @GetMapping(path = "")
    public List<Accommodation> getAllAccommodations() throws Exception {
        return accommodationAdaptor.getAllAccommodations();
    }


    @PostMapping(path = "")
    public ResponseEntity<AccommodationResponse> addAccommodation(@RequestBody AccommodationRequest r) throws UserException {

        AccommodationResponse response = accommodationAdaptor.addAccommodation(r);
        response.setMessage("Accommodation created successfully");
        return ResponseEntity.ok(response);

    }

    @PutMapping(path = "/{accommodation_id}")
    public ResponseEntity<AccommodationResponse> updateAccommodation(@PathVariable(value = "accommodation_id") String accommodation_id, @RequestBody Accommodation accommodation) throws Exception {
        AccommodationResponse response = accommodationAdaptor.updateAccommodation(accommodation_id, accommodation);
        response.setMessage("Accommodation updated successfully");
        return ResponseEntity.ok(response);

    }

}
