package org.armzbot.exception;

import java.io.Serial;

public class AccessDeniedException extends BaseException {


    public AccessDeniedException(String code) {
        super(code);
    }
}
