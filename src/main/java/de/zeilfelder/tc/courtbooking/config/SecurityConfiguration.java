package de.zeilfelder.tc.courtbooking.config;

import de.zeilfelder.tc.courtbooking.entities.Role;
import de.zeilfelder.tc.courtbooking.entities.User;
import de.zeilfelder.tc.courtbooking.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserRepository userRepository;

    public SecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/register").hasAuthority(Role.ADMIN.name())
                )
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .defaultSuccessUrl("/bookings", true)
                );
//                .rememberMe(withDefaults());

        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {

        userRepository.save(
                User.Builder.builder()
                        .email("oliver.zeilfelder@gmail.com")
                        .firstname("Oliver")
                        .lastname("Zeilfelder")
                        .password(passwordEncoder().encode("1234"))
                        .role(Role.ADMIN)
                        .build()
        );

        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found in database"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
