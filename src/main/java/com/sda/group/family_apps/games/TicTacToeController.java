package com.sda.group.family_apps.games;

import com.sda.group.family_apps.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class TicTacToeController {

    @Autowired
    private TicTacToeRepository ticTacToeRepository;

    @Autowired
    private UserRepository userRepository;

    private TicTacToe ticTacToe;

    @GetMapping(value = "/ticTacToe")
    public String showTicTacToeGame(Model model) {
        model.addAttribute("message", "Zapraszamy do gry");
        return "tictactoe";
    }

    @PostMapping(value = "/ticTacToe_Start/{owner}")
    public String startTicTacToeGame(Model model,
                                     @PathVariable String owner,
                                     @ModelAttribute(name = "guest") String guest,
                                     @ModelAttribute(name = "first_player") String firstPlayer) {
        ticTacToe = new TicTacToe();
        prepareGame(model, owner, guest, firstPlayer);
        return "tictactoe";
    }

    @PostMapping(value = "/ticTacToe/{username}")
    public String playTicTacToeGame(Model model,
                                    @PathVariable String username,
                                    @ModelAttribute(name = "board") int boardValue) {
        playGame(model, username, boardValue);
        return "tictactoe";
    }

    private void prepareGame(Model model,
                             @PathVariable String owner,
                             @ModelAttribute(name = "guest") String guest,
                             @ModelAttribute(name = "first_player") String firstPlayer) {
        ticTacToe.setGameOwner(owner);
        ticTacToe.setGuest(guest);
        model.addAttribute("gameBoard", ticTacToe.getBoard());
        if (firstPlayer.equals("owner")) {
            prepareModel(model, ticTacToe.getGameOwner());
        } else {
            prepareModel(model, ticTacToe.getGuest());
        }
    }

    private void prepareModel(Model model, String player) {
        String message = "Ruch zawodnika: " + player;
        int i = 0;
        for (String button : ticTacToe.getBoard()) {
            if (button != null) {
                i += 1;
            }
            if (i == 9) {
                message = "Remis!";
            }
        }
        model.addAttribute("message", message);
        model.addAttribute("player", player);
    }


    private void playGame(Model model,
                          @PathVariable String username,
                          @ModelAttribute(name = "board") int boardValue) {
        ticTacToe.getBoard()[boardValue] = username;
        ticTacToe.setGameSequence(ticTacToe.getGameSequence() + boardValue + "_" + username + " -> ");
        model.addAttribute("gameBoard", ticTacToe.getBoard());
        if (!username.equals(ticTacToe.getGameOwner())) {
            prepareModel(model, ticTacToe.getGameOwner());
        } else {
            prepareModel(model, ticTacToe.getGuest());
        }
        if (ticTacToe.gameValidation(ticTacToe.getBoard(), username) != null) {
            model.addAttribute("message", ticTacToe.gameValidation(ticTacToe.getBoard(), username));
            model.addAttribute("avatar", userRepository.findUserByUsername(username).getAvatar());
            ticTacToe.setWinner(username);
            ticTacToe.setEndDate(LocalDateTime.now());
            ticTacToe.setGameStatus(GameStatus.END);
            ticTacToeRepository.save(ticTacToe);
            model.addAttribute("guest", ticTacToe.getGuest());
        }
    }
}
