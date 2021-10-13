package com.ims.image.app.exception;

public class OperationNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = -6536841467910301905L;

	public OperationNotSupportedException(String msg) {
        super(msg);
    }

    public OperationNotSupportedException(Throwable cause) {
        super(cause);
    }

    public OperationNotSupportedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
