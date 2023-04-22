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

    private final UserRepository userRepository;

    private ReservationObject buildReservationObject(Reservation res) {
        Accommodation acc = res.getAccommodation();

        AccommodationObject acc_obj = new AccommodationObject();
        acc_obj.setAcc_name(acc.getAcc_name());
        acc_obj.setMax_guest(acc.getMax_guest());
        acc_obj.setBedroom(acc.getBedroom());
        acc_obj.setBathrooms(acc.getBathrooms());
        acc_obj.setPrice(acc.getPrice());
        acc_obj.setDescription(acc.getDescription());
        acc_obj.setRoom_address(acc.getRoom_address());
        acc_obj.setRoom_street(acc.getRoom_street());
        acc_obj.setRoom_state(acc.getRoom_state());
        acc_obj.setRoom_country(acc.getRoom_country());
        acc_obj.setRoom_country_code(acc.getRoom_country_code());
        acc_obj.setCancellation_policy(acc.getCancellation_policy());
        acc_obj.setLocation_lat(acc.getLocation_lat());
        acc_obj.setLocation_long(acc.getLocation_long());
        acc_obj.setHas_internet(acc.isHas_internet());
        acc_obj.setHas_tv(acc.isHas_tv());
        acc_obj.setHas_kitchen(acc.isHas_kitchen());
        acc_obj.setHas_air_conditioning(acc.isHas_air_conditioning());
        acc_obj.setHas_heating(acc.isHas_heating());
        acc_obj.setMinimum_nights(acc.getMinimum_nights());
        acc_obj.setMaximum_nights(acc.getMaximum_nights());
        acc_obj.setRoom_type(acc.getRoom_type());
        acc_obj.set_active(acc.is_active());
        acc_obj.setUser_id(acc.getUser().getId());
        acc_obj.setAccommodation_id(acc.getId());

        ReservationObject reservationObject = ReservationObject.builder()
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

        return reservationObject;
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
        List<Reservation> repository = reservationRepository.findByAccommodationId(acc_id);
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
