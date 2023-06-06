package de.zeilfelder.tc.courtbooking.authentication;

import de.zeilfelder.tc.courtbooking.entities.User;
import de.zeilfelder.tc.courtbooking.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
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
        userRepository.save(user);
    }

    public boolean userAlreadyExists(String username) {
        return userRepository.existsByEmail(username);
    }

    public void changePassword(User user, String oldPassword, String newPassword) throws OldPasswordInvalidException {
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new OldPasswordInvalidException("Old password not correct");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    // TODO handle limited booking rights (only court 3) for new TI role
}
