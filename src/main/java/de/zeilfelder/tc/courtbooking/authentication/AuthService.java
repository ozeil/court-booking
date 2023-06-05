package de.zeilfelder.tc.courtbooking.authentication;

import de.zeilfelder.tc.courtbooking.entities.User;
import de.zeilfelder.tc.courtbooking.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserRegistrationDto request) throws UserAlreadyExistsException {
        if (userAlreadyExists(request.getEmail())) {
            throw new UserAlreadyExistsException("There is an account with that email address: "
                    + request.getEmail());
        }
        var user = User.Builder.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        repository.save(user);
    }

    public boolean userAlreadyExists(String username) {
        return repository.existsByEmail(username);
    }

    // TODO handle limited booking rights (only court 3) for new TI role
}
