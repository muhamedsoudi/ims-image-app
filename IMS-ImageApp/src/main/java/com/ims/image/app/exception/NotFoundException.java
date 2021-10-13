package com.ims.image.app.exception;

public class NotFoundException extends RuntimeException {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8021812700770914121L;

	public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
