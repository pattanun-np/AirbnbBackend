package org.armzbot.exception;

public class AccommodationException extends BaseException {

    public AccommodationException(String message) {
        super(message);
    }

    public AccommodationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccommodationException(Throwable cause) {
        super(cause);
    }

    public AccommodationException(String msg, int code) {
        super("Accommodation." + msg, code);
    }

    public static AccommodationException notFround() {

        return new AccommodationException("notFound", 404);
    }


}
