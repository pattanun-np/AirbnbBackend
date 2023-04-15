package org.armzbot.exception;

import java.io.Serial;

public class AccessDeniedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;


    private String message;

}
