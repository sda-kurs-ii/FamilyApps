package com.sda.group.family_apps.games;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;

@Getter
@Setter
public class TicTacToe {

    private String[] board = new String[9];

    public String gameValidation(String[] boardCurrent, String player) {

        if (player.equalsIgnoreCase(boardCurrent[0]) &&
                player.equalsIgnoreCase(boardCurrent[1]) &&
                player.equalsIgnoreCase(boardCurrent[2]) ||
                player.equalsIgnoreCase(boardCurrent[3]) &&
                        player.equalsIgnoreCase(boardCurrent[4]) &&
                        player.equalsIgnoreCase(boardCurrent[5]) ||
                player.equalsIgnoreCase(boardCurrent[6]) &&
                        player.equalsIgnoreCase(boardCurrent[7]) &&
                        player.equalsIgnoreCase(boardCurrent[8]) ||
                player.equalsIgnoreCase(boardCurrent[0]) &&
                        player.equalsIgnoreCase(boardCurrent[4]) &&
                        player.equalsIgnoreCase(boardCurrent[8]) ||
                player.equalsIgnoreCase(boardCurrent[2]) &&
                        player.equalsIgnoreCase(boardCurrent[4]) &&
                        player.equalsIgnoreCase(boardCurrent[6]) ||
                player.equalsIgnoreCase(boardCurrent[0]) &&
                        player.equalsIgnoreCase(boardCurrent[3]) &&
                        player.equalsIgnoreCase(boardCurrent[6]) ||
                player.equalsIgnoreCase(boardCurrent[1]) &&
                        player.equalsIgnoreCase(boardCurrent[4]) &&
                        player.equalsIgnoreCase(boardCurrent[7]) ||
                player.equalsIgnoreCase(boardCurrent[2]) &&
                        player.equalsIgnoreCase(boardCurrent[5]) &&
                        player.equalsIgnoreCase(boardCurrent[8])) {
            return "Zwycięża gracz: " + player;
        }
        return null;
    }
}
