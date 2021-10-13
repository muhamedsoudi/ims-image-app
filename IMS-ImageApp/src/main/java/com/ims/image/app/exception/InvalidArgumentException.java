package com.ims.image.app.exception;

public class InvalidArgumentException extends RuntimeException {

    private static final long serialVersionUID = 4570573798200354363L;

    public InvalidArgumentException(String msg) {
        super(msg);
    }

    public InvalidArgumentException(Throwable cause) {
        super(cause);
    }

    public InvalidArgumentException(String msg, Throwable cause) {
        super(msg, cause);
    }

}