package org.armzbot.exception;

public class UserException extends BaseException {
    public UserException(String msg, int code) {
        super("User." + msg, code);
    }

    public static UserException notFound() {
        return new UserException("notFound", 404);
    }

    public static UserException unauthorized() {
        return new UserException("unauthorized", 401);
    }

    public static UserException alreadyExists(

    ) {
        return new UserException("alreadyExists", 409);
    }

    public static UserException invalidToken() {
        return new UserException("invalidToken", 401);
    }

    public static UserException invalidEmail() {
        return new UserException("invalidEmail", 400);
    }

    public static UserException invalidPassword() {

        return new UserException("invalidPassword", 401);
    }
    public static UserException invalidPasswordPattern() {

        return new UserException("invalidPassword password must longer than 8 character", 400);
    }

    public static UserException invalidName() {
        return new UserException("invalidName", 400);
    }

    public static UserException invalidLastName() {
        return new UserException("invalidLastName", 400);
    }

    public static UserException invalidPhone() {

        return new UserException("invalidPhone", 400);
    }


}


