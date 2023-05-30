package de.zeilfelder.tc.courtbooking.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationDto(String firstname,
                                  String lastname,
                                  @Email String email,
                                  @NotBlank @Size(min = 8, message = "Password must be at least 6 characters long") String password) {
}
