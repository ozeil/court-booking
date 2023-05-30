package de.zeilfelder.tc.courtbooking.authentication;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping
    public String register(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        if (registrationService.userAlreadyExists(userDto.email())) {
            result.rejectValue("email", "0", "Nutzer mit E-Mail existiert bereits");
            return "register";
        }

        registrationService.register(userDto);

        return "redirect:/login?registered";
    }

}