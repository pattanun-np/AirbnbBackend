package org.armzbot.controller;


import lombok.RequiredArgsConstructor;
import org.armzbot.adaptor.ReservationAdaptor;
import org.armzbot.dto.ReservationRequest;
import org.armzbot.entity.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationAdaptor reservationAdaptor;


    // Get all reservations by user id
    @GetMapping(path = "")
    public ResponseEntity<List<Reservation>> getAllReservationsByUserId(
            String user_id) throws Exception {
        List<Reservation> response = reservationAdaptor.getAllReservationsByUserId();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get reservation by id
    @GetMapping(path = "/{reserve_id}")
    public ResponseEntity<Reservation> getReservationById(
            @PathVariable(value = "reserve_id") String reserve_id) throws Exception {
        Reservation response = reservationAdaptor.getReservationById(reserve_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Create a reservation
    @PostMapping(path = "/reservations")
    public void addReservation(@RequestBody ReservationRequest r) throws Exception {
        reservationAdaptor.addReservation(r);

    }


    @PostMapping(path = "/{reserve_id}")
    public void updateReservation(
            @PathVariable(value = "reserve_id") String reserve_id,
            @RequestBody ReservationRequest r) throws Exception {
        reservationAdaptor.updateReservation(reserve_id, r);
    }

    @PutMapping(path = "/reservations/{reserve_id}")
    public ResponseEntity<Reservation> deleteReservationById(
            @PathVariable(value = "reserve_id") String reserve_id) throws Exception {
        reservationAdaptor.deleteReservationById(reserve_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{user_id}/reservations")
    public ResponseEntity<List<Reservation>> deleteAllReservationsOfUser(
            @PathVariable(value = "user_id") String user_id) {
        reservationAdaptor.deleteAllReservationsOfUser(user_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping(path = "/reservations/{reserve_id}/payment_status")
    public void updatePaymentStatus(
            @PathVariable(value = "reserve_id") String reserve_id,
            @RequestBody String payment_status) throws Exception {
        reservationAdaptor.updatePaymentStatus(reserve_id, payment_status);
    }

}
