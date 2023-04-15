package org.armzbot.exception;

public class UserException extends RuntimeException {
    public UserException(String code) {
        super("User." + code);
    }

    public static UserException notFound() {
        return new UserException("notFound");
    }

    public static UserException unauthorized() {
        return new UserException("unauthorized");
    }

    public static UserException alreadyExists() {
        return new UserException("alreadyExists");
    }

    public static UserException invalidToken() {
        return new UserException("invalidToken");
    }

    public static UserException invalidEmail() {
        return new UserException("invalidEmail");
    }

    public static UserException invalidPassword() {
        return new UserException("invalidPassword");
    }

    public static UserException invalidName() {
        return new UserException("invalidName");
    }

    public static UserException NotFound() {
        return new UserException("NotFound");
    }

    public static UserException invalidPhone() {
        return new UserException("invalidPhone");
    }
}


