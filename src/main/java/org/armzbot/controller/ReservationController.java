package org.armzbot.controller;


import org.armzbot.models.Reservation;
import org.armzbot.services.ReservationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class ReservationController {

    private final ReservationServices reservationServices;

    @Autowired
    public ReservationController(ReservationServices reservationServices) {
        this.reservationServices = reservationServices;
    }

    @GetMapping(path = "/users/{user_id}/reservations")
    public ResponseEntity<List<Reservation>> getAllReservationsByUserId(
            @PathVariable(value = "user_id") String user_id) throws IOException {
        List<Reservation> response = reservationServices.getAllReservationsByUserId(user_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/reservations/{reserve_id}")
    public ResponseEntity<Reservation> getReservationById(
            @PathVariable(value = "reserve_id") String reserve_id) {
        Reservation response = reservationServices.getReservationById(reserve_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/User/{user_id}/reservations")
    public void addReservation(@PathVariable(value = "user_id") String user_id, @RequestBody Reservation reservation) {
        reservationServices.addReservation(user_id, reservation);
    }

    @PostMapping(path = "/reservations/{reserve_id}")
    public void updateReservation(
            @PathVariable(value = "reserve_id") String reserve_id,
            @RequestBody Reservation reservation) {
        reservationServices.updateReservation(reserve_id, reservation);
    }

    @DeleteMapping(path = "/reservations/{reserve_id}")
    public void deleteReservationById(
            @PathVariable(value = "reserve_id") String reserve_id) {
        reservationServices.deleteReservationById(reserve_id);
    }

    @DeleteMapping(path = "/user/{user_id}/reservations")
    public void deleteAllReservationsOfUser(
            @PathVariable(value = "user_id") String user_id) {
        reservationServices.deleteAllReservationsOfUser(user_id);
    }




}
