package com.sda.group.family_apps.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/register")
    public String showForm(Model model) {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        model.addAttribute("userDto", userRegistrationDTO); //sluzy jako pusta foremka na dane + polaczenie pól dla springa
        model.addAttribute("countries", Countries.values()); //zapewnia wartosci w dropdownie
        return "registerForm"; //sluzy do wyswietlenia konkretnego htmla o takiej nazwie (MVC)
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute(name = "userDto") @Valid UserRegistrationDTO userDto,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("countries", Countries.values());
            return "registerForm";
        }
        userRegistrationService.registerUser(userDto);
        return "loginForm";
    }

    @GetMapping(value = "/{username}")
    public String showAccountDetails(Model model, @PathVariable String username){
        model.addAttribute("user", userRepository.findUserByUsername(username));
        return "userDetails";
    }
}
