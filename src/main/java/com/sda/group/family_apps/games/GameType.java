package com.sda.group.family_apps.games;

import com.sda.group.family_apps.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "gametypes")
public class GameType extends BaseEntity {


    private String name;
    private String description;
    private String image;
    private Integer minPlayers;
    private Integer maxPlayers;
    private String gameHref;

}
