package org.armzbot.controller;


import lombok.RequiredArgsConstructor;
import org.armzbot.adaptor.ReservationAdaptor;
import org.armzbot.dto.ReservationObject;
import org.armzbot.dto.ReservationRequest;
import org.armzbot.dto.ReservationResponse;
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
    public ResponseEntity<List<ReservationObject>> getAllReservationsByUserId(
            String user_id) throws Exception {
        List<ReservationObject> response = reservationAdaptor.getAllReservationsByUserId();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get reservation by id
    @GetMapping(path = "/{reserve_id}")
    public ResponseEntity<ReservationObject> getReservationById(
            @PathVariable(value = "reserve_id") String reserve_id) throws Exception {
        ReservationObject response = reservationAdaptor.getReservationById(reserve_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Create a reservation
    @PostMapping(path = "")
    public ResponseEntity<ReservationResponse> addReservation(@RequestBody ReservationRequest r) throws Exception {
        ReservationResponse response = reservationAdaptor.addReservation(r);
        response.setMessage("Reservation created successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @PutMapping(path = "/{reserve_id}")
    public ResponseEntity<ReservationResponse> updateReservation(
            @PathVariable(value = "reserve_id") String reserve_id,
            @RequestBody ReservationRequest r) throws Exception {
        ReservationResponse response = reservationAdaptor.updateReservation(reserve_id, r);
        response.setMessage("Reservation updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{reserve_id}")
    public ResponseEntity<ReservationResponse> deleteReservationById(
            @PathVariable(value = "reserve_id") String reserve_id) throws Exception {
        ReservationResponse response = reservationAdaptor.deleteReservationById(reserve_id);
        response.setMessage("Reservation deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{user_id}/reservations")
    public ResponseEntity<ReservationResponse> deleteAllReservationsOfUser(
            @PathVariable(value = "user_id") String user_id) {
        ReservationResponse response = reservationAdaptor.deleteAllReservationsOfUser(user_id);
        response.setMessage("All reservations of user deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping(path = "/{reserve_id}/payment_status")
    public ResponseEntity<ReservationResponse> updatePaymentStatus(
            @PathVariable(value = "reserve_id") String reserve_id,
            @RequestBody String payment_status) throws Exception {
        ReservationResponse response = reservationAdaptor.updatePaymentStatus(reserve_id, payment_status);
        response.setMessage("Payment status updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
