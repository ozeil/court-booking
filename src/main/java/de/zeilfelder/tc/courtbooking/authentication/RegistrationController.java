package de.zeilfelder.tc.courtbooking.authentication;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    @PostMapping
    public String register(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        if (registrationService.userAlreadyExists(userDto.getEmail())) {
            result.rejectValue("email", "0", "Nutzer mit E-Mail existiert bereits");
            return "register";
        }

        registrationService.register(userDto);

        return "redirect:/login?registered";
    }

}