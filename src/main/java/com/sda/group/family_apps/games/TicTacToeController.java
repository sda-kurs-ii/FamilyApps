package com.sda.group.family_apps.games;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TicTacToeController {

    TicTacToe ticTacToe;

    @GetMapping(value = "/ticTacToe")
    public String showTicTacToeGame(Model model) {
        ticTacToe = new TicTacToe();
        model.addAttribute("gameBoard", ticTacToe.getBoard());
        return "tictactoe";
    }

    @PostMapping(value = "/ticTacToe")
    public String actionTicTacToeGame(Model model, @ModelAttribute(name = "player") String player, @ModelAttribute(name = "board") String board) {
        ticTacToe.getBoard()[Integer.parseInt(board)] = player;
        if (ticTacToe.gameValidation(ticTacToe.getBoard(), player) != null) {
            model.addAttribute("winner", ticTacToe.gameValidation(ticTacToe.getBoard(), player));
            ticTacToe = new TicTacToe();
            model.addAttribute("gameBoard", ticTacToe.getBoard());
            return "tictactoe";
        } else {
            model.addAttribute("gameBoard", ticTacToe.getBoard());
            if ("X".equalsIgnoreCase(player)) {
                model.addAttribute("O", "true");
                model.addAttribute("X", "false");
            } else {
                model.addAttribute("X", "true");
                model.addAttribute("O", "false");
            }
            return "tictactoe";
        }
    }
}
