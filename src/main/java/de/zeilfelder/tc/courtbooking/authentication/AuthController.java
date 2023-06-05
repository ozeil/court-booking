package de.zeilfelder.tc.courtbooking.authentication;

import de.zeilfelder.tc.courtbooking.entities.Role;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        model.addAttribute("roles", List.of(Role.values()));
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult bindingResult) {
        try {
            authService.register(userDto);
        } catch (UserAlreadyExistsException e) {
            bindingResult.rejectValue("email", "0", "Nutzer mit E-Mail existiert bereits");
            return "register";
        }

        return "registration-succesful";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // TODO add method to handle batch registration (just use csv (opencsv) - user can export it from excel)

    // TODO let user change password and send email with initial pw
}