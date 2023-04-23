package org.armzbot.controller;

import lombok.RequiredArgsConstructor;
import org.armzbot.adaptor.AccommodationAdaptor;
import org.armzbot.adaptor.ReservationAdaptor;
import org.armzbot.adaptor.UserAdaptor;
import org.armzbot.dto.AccommodationObject;
import org.armzbot.dto.ReservationObject;
import org.armzbot.dto.UserProfile;
import org.armzbot.entity.Reservation;
import org.armzbot.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserAdaptor userAdaptor;
    private final AccommodationAdaptor accommodationAdaptor;

    private final ReservationAdaptor reservationAdaptor;

    ///api/v1/user/profile
    @GetMapping("/profile")
    public ResponseEntity<UserProfile> getUserProfile() throws UserException {
        UserProfile response = userAdaptor.getUserProfile();
        return ResponseEntity.ok(response);


    }

    ///api/v1/user/my-accommodation
    @GetMapping("/my-accommodation")
    public ResponseEntity<List<AccommodationObject>> getAccommodationByUserId() throws Exception {

        List<AccommodationObject> res = accommodationAdaptor.getAccommodationsByUserId();
        return ResponseEntity.ok(res);
    }


    @GetMapping(path = "/my-reservations")

    public ResponseEntity<List<ReservationObject>> getAllReservationsByUserId() throws Exception {

        List<ReservationObject> response = reservationAdaptor.getAllReservationsByUserId();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
