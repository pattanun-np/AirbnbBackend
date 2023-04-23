package org.armzbot.exception;

public class ReservationException extends BaseException {
    public ReservationException(String message) {
        super(message);
    }

    public ReservationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReservationException(Throwable cause) {
        super(cause);
    }

    public ReservationException(String msg, int code) {
        super("Reservation." + msg, code);
    }
    public static ReservationException notFound() {

        return new ReservationException("notFound", 404);
    }

    public static ReservationException sameUser() {

        return  new ReservationException("user reserved own accommodation", 400);
    }

    public static ReservationException overGuests() {

        return new ReservationException(
                "The amount of guests is more than can be accommodated"
                , 400
        );
    }

    public static ReservationException overDays() {

        return new ReservationException(
                "The number of days is inconsistent with the accommodation"
                , 400
        );
    }

    public static ReservationException pastCheckOut() {

        return new ReservationException(
                "check in and check out are inconsistent"
                , 400
        );
    }

    public static ReservationException pastCheckIn() {

        return new ReservationException(
                "check in and current time are inconsistent"
                , 400
        );
    }

    public static ReservationException repeatBooking() {

        return new ReservationException(
                "This reservation's time has already reserved"
                , 400
        );
    }

    public static ReservationException sameDay() {

        return new ReservationException(
                "check in and check out are the same day"
                , 400
        );
    }
}
