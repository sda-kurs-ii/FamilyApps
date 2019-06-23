package com.sda.group.family_apps.games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GamesController {

    @Autowired
    private GameTypeRepository gameTypeRepository;


    @GetMapping(value = "/games")
    public String showGameTypes(Model model){
        model.addAttribute("gameTypeList", gameTypeRepository.findAll());
        return "games";
    }
}
