package org.armzbot.adaptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.armzbot.dto.ReservationRequest;
import org.armzbot.entity.Reservation;
import org.armzbot.exception.UserException;
import org.armzbot.services.ReservationService;
import org.armzbot.utils.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReservationAdaptor {

    private final ReservationService reservationService;


    public List<Reservation> getAllReservationsByUserId() throws Exception {
        Optional<String> opt = SecurityUtil.getCurrentUserId();

        if (opt.isEmpty()) {
            throw UserException.unauthorized();
        }
        String userId = opt.get();

        List<Reservation> res = reservationService.getAllReservationsByUserId(userId);
        System.out.println("res = " + res);
        return res;
    }

    public List<Reservation> getAllReservationsByUserId(String user_id) throws Exception {
        return reservationService.getAllReservationsByUserId(user_id);

    }

    public Reservation getReservationById(String reserve_id) throws Exception {
        return reservationService.getReservationById(reserve_id);
    }

    public void addReservation(ReservationRequest r) throws Exception {

        Optional<String> opt = SecurityUtil.getCurrentUserId();

        if (opt.isEmpty()) {
            throw UserException.unauthorized();
        }
        String userId = opt.get();

        Reservation reservation = Reservation.builder()
                .checkIn(r.getCheckIn())
                .checkOut(r.getCheckOut())
                .payment_status("PENDING")
                .build();
        reservationService.addReservation(userId, reservation);
    }


    public void updatePaymentStatus(String reserve_id, String payment_status) throws Exception {
        Reservation reservation = reservationService.getReservationById(reserve_id);
        reservation.setPayment_status(payment_status);
        reservationService.updateReservation(reserve_id, reservation);
    }

    public void updateReservation(String reserve_id, ReservationRequest r) throws Exception {
        Reservation reservation = Reservation.builder()
                .checkIn(r.getCheckIn())
                .checkOut(r.getCheckOut())
                .build();
        reservationService.updateReservation(reserve_id, reservation);
    }

    public void deleteReservationById(String reserve_id) throws Exception {
        reservationService.deleteReservationById(reserve_id);
    }


    public void deleteAllReservationsOfUser(String userId) {

        reservationService.deleteAllReservationsOfUser(userId);
    }
}
