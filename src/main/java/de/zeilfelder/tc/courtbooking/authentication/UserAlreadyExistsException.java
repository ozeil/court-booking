package de.zeilfelder.tc.courtbooking.authentication;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
