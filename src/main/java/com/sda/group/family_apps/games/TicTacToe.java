package com.sda.group.family_apps.games;

import com.sda.group.family_apps.BaseEntity;
import lombok.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
@Table(name = "tictactoe_games")
public class TicTacToe extends BaseEntity {

    private String gameSequence = "";
    private String gameOwner;
    private String guest;
    private GameStatus gameStatus = GameStatus.NEW;
    private String winner;
    private LocalDateTime startDate = LocalDateTime.now();
    private LocalDateTime endDate;
    @Transient
    private String[] board = new String[9];

    String gameValidation(String[] boardCurrent, String player) {

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
