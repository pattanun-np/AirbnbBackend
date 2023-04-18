package org.armzbot.controller;

import lombok.RequiredArgsConstructor;
import org.armzbot.adaptor.AccommodationAdaptor;
import org.armzbot.dto.AccommodationObject;
import org.armzbot.dto.AccommodationRequest;
import org.armzbot.dto.AccommodationResponse;
import org.armzbot.entity.Accommodation;
import org.armzbot.exception.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accommodation")
@RequiredArgsConstructor
public class AccommodationController {
    private final AccommodationAdaptor accommodationAdaptor;


    ///api/v1/accommodation/{accommodation_id}

    @GetMapping(path = "/{accommodation_id}")
    public ResponseEntity<AccommodationObject> getAccommodationById(@PathVariable(value = "accommodation_id") String accommodation_id) throws Exception {
        AccommodationObject res = accommodationAdaptor.getAccommodationById(accommodation_id);
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<AccommodationObject>> getAllAccommodations() {
        List<AccommodationObject> res = accommodationAdaptor.getAllAccommodations();
        return ResponseEntity.ok(res);
    }

    ///api/v1/accommodation

    @PostMapping(path = "")
    public ResponseEntity<AccommodationResponse> addAccommodation(@RequestBody AccommodationRequest r) throws UserException {

        AccommodationResponse response = accommodationAdaptor.addAccommodation(r);
        response.setMessage("Accommodation created successfully");
        return ResponseEntity.ok(response);

    }

    ///api/v1/accommodation/{accommodation_id}
    @PutMapping(path = "/{accommodation_id}")
    public ResponseEntity<AccommodationResponse> updateAccommodation(@PathVariable(value = "accommodation_id") String accommodation_id, @RequestBody Accommodation accommodation) throws Exception {
        AccommodationResponse response = accommodationAdaptor.updateAccommodation(accommodation_id, accommodation);
        response.setMessage("Accommodation updated successfully");
        return ResponseEntity.ok(response);

    }

}

