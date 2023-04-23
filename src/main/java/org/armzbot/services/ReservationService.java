package org.armzbot.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.armzbot.dto.AccommodationObject;
import org.armzbot.dto.ReservationObject;
import org.armzbot.entity.Accommodation;
import org.armzbot.entity.Reservation;
import org.armzbot.exception.BaseException;
import org.armzbot.exception.ReservationException;
import org.armzbot.repository.ReservationRepository;
import org.armzbot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final AccommodationService accommodationService;

    private final UserRepository userRepository;

    private ReservationObject buildReservationObject(Reservation res) {
        Accommodation acc = res.getAccommodation();

        AccommodationObject acc_obj = accommodationService.BuildAccommodationObj(acc);

        return ReservationObject.builder()
                .reserve_id(res.getId())
                .price(res.getPrice())
                .checkIn(res.getCheckIn())
                .checkOut(res.getCheckOut())
                .guest_amt(res.getGuest_amt())
                .total_price(res.getTotal_price())
                .payment_status(res.getPayment_status())
                .is_active(res.is_active())
                .user_id(res.getUser().getId())
                .accommodation(acc_obj)
                .build();
    }

    public List<ReservationObject> getAllReservationsByUserId(String user_id) {
        ArrayList<ReservationObject> reservations = new ArrayList<>();
        List<Reservation> repository = reservationRepository.findByUserId(user_id);

        for (Reservation r : repository) {
            if (r.is_active()) {
                reservations.add(buildReservationObject(r));
            }
        }

        return  reservations;
    }

    public ReservationObject getReservationById(String reserve_id) throws BaseException {
        Optional<Reservation> repository = reservationRepository.findById(reserve_id);

        if (repository.isEmpty()) {
            throw ReservationException.notFound();
        }
        if (!repository.get().is_active()) {
            throw ReservationException.notFound();
        }

        Reservation res = repository.get();

        return buildReservationObject(res);
    }

    public Reservation getReservationEntityById(String reserve_id) throws BaseException {
        Optional<Reservation> repository = reservationRepository.findById(reserve_id);

        if (repository.isEmpty()) {
            throw ReservationException.notFound();
        }
        if (!repository.get().is_active()) {
            throw ReservationException.notFound();
        }

        Reservation res = repository.get();

        return res;
    }

    public void addReservation(String user_id, Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void updateReservation(String reserve_id, Reservation reservation) throws BaseException {

        Optional<Reservation> repository = reservationRepository.findById(reserve_id);

        if (repository.isEmpty()) {
            throw ReservationException.notFound();
        }
        Reservation result = repository.get();

        result.setGuest_amt(reservation.getGuest_amt());
        result.setCheckIn(reservation.getCheckIn());
        result.setCheckOut(reservation.getCheckOut());
        result.setPayment_status(reservation.getPayment_status());
        result.set_active(reservation.is_active());
        result.setPrice(reservation.getPrice());
        result.setTotal_price(reservation.getTotal_price());
        result.setUser(reservation.getUser());
        result.setAccommodation(reservation.getAccommodation());

        reservationRepository.save(result);
    }

    public void deleteReservationById(String reserve_id) throws BaseException {
        Optional<Reservation> repository = reservationRepository.findById(reserve_id);

        if (repository.isEmpty()) {
            return;
        }

        Reservation deleteReserve = repository.get();
        deleteReserve.setIsActive(false);

        reservationRepository.save(deleteReserve);
    }

    public void deleteAllReservationsOfUser(String user_id) {

        List<Reservation> repositories = reservationRepository.findByUserId(user_id);

        for (Reservation deleteReserve : repositories) {
            deleteReserve.setIsActive(false);
            reservationRepository.save(deleteReserve);
        }
    }

    public List<List<Long>> getTimeCheckInOutByAccommodationId(String acc_id) {
        List<Reservation> repository = reservationRepository.findByAccommodationID(acc_id);
        ArrayList<Long> timeCheckIn = new ArrayList<>();
        ArrayList<Long> timeCheckOut = new ArrayList<>();
        List<List<Long>> timeCheckInOut = new ArrayList<>();

        for (Reservation r : repository) {
            if (r.is_active()) {
                timeCheckIn.add(r.getCheckIn().getTime() + (3600000 * 7));
                timeCheckOut.add(r.getCheckOut().getTime() + (3600000 * 7));
            }
        }
        timeCheckInOut.add(timeCheckIn);
        timeCheckInOut.add(timeCheckOut);

        return timeCheckInOut;
    }
}
