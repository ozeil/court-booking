package de.zeilfelder.tc.courtbooking.authentication;

import de.zeilfelder.tc.courtbooking.entities.Role;
import de.zeilfelder.tc.courtbooking.entities.User;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/update-password")
    public String showUpdatePasswordForm(Model model) {
        model.addAttribute("passwordUpdateDto", new PasswordUpdateDto());
        return "update-password";
    }

    @PostMapping("/update-password")
    public String updatePassword(@ModelAttribute @Valid PasswordUpdateDto passwordUpdateDto, Model model, @AuthenticationPrincipal User user) {
        try {
            authService.changePassword(user, passwordUpdateDto.getOldPassword(), passwordUpdateDto.getNewPassword());
        } catch (OldPasswordInvalidException e) {
            model.addAttribute("isOldPasswordInvalid", true);
        }
        model.addAttribute("isPasswordChangeSuccesful", true);
        return "update-password";
    }

    // TODO add method to handle batch registration (just use csv (opencsv) - user can export it from excel)

    // TODO let user change password and send email with initial pw AND/OR let admin change password

    // TODO user deletion
}