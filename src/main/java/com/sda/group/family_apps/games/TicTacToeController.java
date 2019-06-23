package com.sda.group.family_apps.games;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TicTacToeController {

    @GetMapping (value = "/ticTacToe")
    public String showTicTacToeGame(){
        return "tictactoe";
    }

    @PostMapping (value = "/ticTacToe")
    public String actionTicTacToeGame(){
        return "tictactoe";
    }

}
