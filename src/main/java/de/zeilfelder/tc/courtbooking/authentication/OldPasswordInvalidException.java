package de.zeilfelder.tc.courtbooking.authentication;

public class OldPasswordInvalidException extends Exception {

    public OldPasswordInvalidException(String message) {
        super(message);
    }
}
