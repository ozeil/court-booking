package de.zeilfelder.tc.courtbooking.authentication;

import jakarta.validation.constraints.Email;

public class UserAlreadyExistsException extends Throwable {
    public UserAlreadyExistsException(@Email String s) {
    }
}
