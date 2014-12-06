package com.nationalappsbd.hackathon.prottoyee.webapp.exception;

/**
 * @author Abdullah Al Mamun Oronno
 */
public class InvalidCredentialException extends RuntimeException {

    public InvalidCredentialException() {
        super("InvalidCredentialException");
    }

    public InvalidCredentialException(String message) {
        super(message);
    }
}
