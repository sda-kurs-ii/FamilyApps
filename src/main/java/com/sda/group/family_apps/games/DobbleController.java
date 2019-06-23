package com.sda.group.family_apps.games;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DobbleController {

    @GetMapping(value = "/dobble")
    public String showDobble(){
        return "dobble";
    }
}
