package com.sda.group.family_apps.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserListController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/list")
    public String showUserList(Model model) {
        List<User> allUsers = userRepository.findAll();
        model.addAttribute("usersList", allUsers);
        return "userList";
    }

    @GetMapping(value = "/{username}")
    public String showUserDetails(Model model, @PathVariable String username) {
        model.addAttribute("usersList", userRepository.findAll());
        model.addAttribute("user", userRepository.findUserByUsername(username));
        return "userList";
    }

}
