package org.armzbot.exception;

public class FirebaseException extends BaseException {

    public FirebaseException(String message, int code) {
        super(message, code);
    }

    public FirebaseException(String message) {
        super(message);
    }


}
