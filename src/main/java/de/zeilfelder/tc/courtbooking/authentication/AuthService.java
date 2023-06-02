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
        if (userAlreadyExists(request.email())) {
            throw new UserAlreadyExistsException("There is an account with that email address: "
                    + request.email());
        }
        var user = User.Builder.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
        repository.save(user);
    }

    public boolean userAlreadyExists(String username) {
        return repository.existsByEmail(username);
    }

    // TODO handle limited booking rights (only court 3) for new TI role
}
