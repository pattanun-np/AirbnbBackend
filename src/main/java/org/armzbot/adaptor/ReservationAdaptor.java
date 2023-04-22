package org.armzbot.adaptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.armzbot.dto.ReservationObject;
import org.armzbot.dto.ReservationRequest;
import org.armzbot.dto.ReservationResponse;
import org.armzbot.entity.Accommodation;
import org.armzbot.entity.Reservation;
import org.armzbot.entity.User;
import org.armzbot.exception.ReservationException;
import org.armzbot.exception.UserException;
import org.armzbot.services.AccommodationService;
import org.armzbot.services.ReservationService;
import org.armzbot.services.UserService;
import org.armzbot.utils.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReservationAdaptor {

    private final ReservationService reservationService;
    private final AccommodationService accommodationService;
    private final UserService userService;

    private int getReserveDays(Date checkIn, Date checkOut) throws ReservationException {

        Calendar current = new GregorianCalendar();
        current.set(Calendar.SECOND, 0);
        current.set(Calendar.MINUTE, 0);
        current.set(Calendar.HOUR_OF_DAY, 0);

        long diffCurrent = checkIn.getTime() - current.getTime().getTime();

        // you check in at the past
        if (diffCurrent < 0) {
            throw ReservationException.pastCheckIn();
        }

        long diffInMs = checkOut.getTime() - checkIn.getTime();

        // you check out in the past
        if (diffInMs < 0) {
            throw  ReservationException.pastCheckOut();
        }
        // you can't check in and out on the same day
        if (diffInMs == 0) {
            throw  ReservationException.sameDay();
        }

        int reserveDay = (int) TimeUnit.DAYS.convert(diffInMs, TimeUnit.MILLISECONDS);

        return reserveDay;
    }


    public List<ReservationObject> getAllReservationsByUserId() throws Exception {
        Optional<String> opt = SecurityUtil.getCurrentUserId();

        if (opt.isEmpty()) {
            throw UserException.unauthorized();
        }
        String userId = opt.get();

        List<ReservationObject> res = reservationService.getAllReservationsByUserId(userId);

        return res;
    }

    public List<ReservationObject> getAllReservationsByUserId(String user_id) throws Exception {

        return reservationService.getAllReservationsByUserId(user_id);
    }

    public ReservationObject getReservationById(String reserve_id) throws Exception {

        return reservationService.getReservationById(reserve_id);
    }

    public ReservationResponse addReservation(ReservationRequest r) throws Exception {

        // auth
        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if (opt.isEmpty()) {
            throw UserException.unauthorized();
        }
        String userId = opt.get();
        Optional<User> optUser = userService.findById(userId);
        if (optUser.isEmpty()) {
            try {
                throw UserException.notFound();
            } catch (UserException e) {
                throw new RuntimeException(e);
            }
        }

        // User
        User user = optUser.get();

        // Accommodation
        Accommodation accommodation = accommodationService.getAccommodationEntityById(r.getAcc_id());

        // reservation's days
        int reserveDays = getReserveDays(r.getCheckIn(),r.getCheckOut());

        // same between user and host exception
        User host = accommodation.getUser();
        if (user.getId().equals(host.getId())) {
            throw ReservationException.sameUser();
        }

        // guest amount overs maximum guest exception
        if (r.getGuest_amt() > accommodation.getMax_guest()) {
            throw ReservationException.overGuests();
        }

        // reservation's days over than maximum nights and less than minimum nights exception
        if(reserveDays > accommodation.getMaximum_nights()
                && reserveDays < accommodation.getMinimum_nights()) {
            throw ReservationException.overDays();
        }

        // Repeat booking exception
        List<List<Long>> timeCheckInOut = reservationService.getTimeCheckInOutByAccommodationId(r.getAcc_id());
        List<Long> timeCheckIn = timeCheckInOut.get(0);
        List<Long> timeCheckOut = timeCheckInOut.get(1);
        Collections.sort(timeCheckIn);
        Collections.sort(timeCheckOut);

        if (!timeCheckOut.isEmpty()) {
            for (int i = 0; i <= timeCheckOut.size(); i++) {

                // loop over
                if (i == timeCheckOut.size()) {
                    throw ReservationException.repeatBooking();
                }

                // time in and out are really collision
                // timeCheckIn < r.getCheckOut() < timeCheckOut
                if (r.getCheckOut().getTime() > timeCheckIn.get(i)
                        && r.getCheckOut().getTime() < timeCheckOut.get(i)) {
                    throw ReservationException.repeatBooking();
                }
                // timeCheckIn < r.getCheckIn() < timeCheckOut
                if (r.getCheckIn().getTime() > timeCheckIn.get(i)
                        && r.getCheckIn().getTime() < timeCheckOut.get(i)) {
                    throw ReservationException.repeatBooking();
                }
                // check in and out are comprehensive timeCheckIn and timeCheckOut
                if (r.getCheckIn().getTime() < timeCheckIn.get(i)
                        && r.getCheckOut().getTime() > timeCheckOut.get(i)) {
                    throw  ReservationException.repeatBooking();
                }


                // check time in and out are free
                if (i+1 != timeCheckIn.size()) {

                    // case : has next in queue
                    if (r.getCheckIn().getTime() >= timeCheckOut.get(i)
                            && r.getCheckOut().getTime() <= timeCheckIn.get(i+1)) {
                        break; // this accommodation has free time
                    }
                } else {

                    // case : only 1 queue that no next in queue
                    if (r.getCheckOut().getTime() <= timeCheckIn.get(i)) {
                        break; // this accommodation has free time
                    }
                    if (r.getCheckIn().getTime() >= timeCheckOut.get(i)) {
                        break; // this accommodation has free time
                    }
                }
            }
        }

        // get price and total price
        float price = accommodation.getPrice();
        float total_price = price * reserveDays;

        // build reservation
        Reservation reservation = Reservation.builder()
                .guest_amt(r.getGuest_amt())
                .checkIn(r.getCheckIn())
                .checkOut(r.getCheckOut())
                .payment_status("PENDING")
                .is_active(true)
                .price(price)
                .total_price(total_price)
                .user(user)
                .accommodation(accommodation)
                .build();

        reservationService.addReservation(userId, reservation);

        return new ReservationResponse();
    }


    public ReservationResponse updatePaymentStatus(String reserve_id, String payment_status) throws Exception {

        Reservation reservation = reservationService.getReservationEntityById(reserve_id);
        reservation.setPayment_status(payment_status);

        reservationService.updateReservation(reserve_id, reservation);
        return new ReservationResponse();
    }

    public ReservationResponse updateReservation(String reserve_id, ReservationRequest r) throws Exception {

        /*
        Reservation reservation = reservationService.getReservationEntityById(reserve_id);

        Accommodation accommodation = accommodationService.getAccommodationEntityById(r.getAcc_id());

        reservation.setGuest_amt(r.getGuest_amt());
        reservation.setAccommodation(accommodation);
        reservation.setCheckIn(r.getCheckIn());
        reservation.setCheckOut(r.getCheckOut());

        reservationService.updateReservation(reserve_id, reservation);
         */

        reservationService.deleteReservationById(reserve_id);
        addReservation(r);

        return new ReservationResponse();
    }

    public ReservationResponse deleteReservationById(String reserve_id) throws Exception {

        reservationService.deleteReservationById(reserve_id);

        return new ReservationResponse();
    }


    public ReservationResponse deleteAllReservationsOfUser(String userId) {

        reservationService.deleteAllReservationsOfUser(userId);

        return new ReservationResponse();
    }
}
