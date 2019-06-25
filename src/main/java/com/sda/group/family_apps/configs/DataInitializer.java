package com.sda.group.family_apps.configs;

import com.google.common.collect.Sets;
import com.sda.group.family_apps.games.GameType;
import com.sda.group.family_apps.games.GameTypeRepository;
import com.sda.group.family_apps.roles.Role;
import com.sda.group.family_apps.roles.RoleRepository;
import com.sda.group.family_apps.user.User;
import com.sda.group.family_apps.user.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer implements InitializingBean {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GameTypeRepository gameTypeRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (roleRepository.count() == 0) {
            Role role_user = roleRepository.save(new Role("ROLE_USER"));
            Role role_admin = roleRepository.save(new Role("ROLE_ADMIN"));
            User user = User.builder()
                    .username("admin")
                    .avatar("https://cdn1.iconfinder.com/data/icons/man-user-human-profile-avatar-business-person/100/09-1User_3-4-512.png")
                    .passwordHash(passwordEncoder.encode("admin"))
                    .roles(Sets.newHashSet(role_admin))
                    .build();
            userRepository.save(user);
            User normalUser = User.builder()
                    .username("user")
                    .avatar("https://cdn0.iconfinder.com/data/icons/man-user-human-profile-avatar-person-business/100/10B-1User-512.png")
                    .passwordHash(passwordEncoder.encode("user"))
                    .roles(Sets.newHashSet(role_user))
                    .build();
            userRepository.save(normalUser);
            User testUser1 = User.builder()
                    .username("Zuzia")
                    .avatar("http://etui-gsm.pl/wp-content/uploads/2017/11/minion_1.png")
                    .passwordHash(passwordEncoder.encode("Zuzia9"))
                    .roles(Sets.newHashSet(role_user))
                    .email("zuzia@zuzia.pl")
                    .build();
            userRepository.save(testUser1);
            User testUser2 = User.builder()
                    .username("Marysia")
                    .avatar("http://www.tolo.com.pl/galerie/d/digibirds-ptaszek-amber-s8802_12737.jpg")
                    .passwordHash(passwordEncoder.encode("Marysia6"))
                    .roles(Sets.newHashSet(role_user))
                    .email("marysia@marysia.pl")
                    .build();
            userRepository.save(testUser2);
        }

        if (gameTypeRepository.count() == 0 ){
            GameType dobble = GameType.builder()
                    .name("Dobble")
                    .description("To gra, w której nie liczy się wiedza i umiejętności, ale spostrzegawczość, koncentracja i refleks!")
                    .image("https://www.panzabawka.pl/19867-large_default/rebel-dobb01pl-gra-karciana-dobble.jpg")
                    .maxPlayers(2)
                    .minPlayers(1)
                    .gameHref("/dobble")
                    .build();
            gameTypeRepository.save(dobble);

            GameType ticTacToe = GameType.builder()
                    .name("TicTacToe")
                    .description("Gra strategiczna rozgrywana przez dwóch graczy, najczęściej na kartce papieru w kratkę.")
                    .image("https://cdn.pixabay.com/photo/2013/07/12/15/56/tic-tac-toe-150614_960_720.png")
                    .minPlayers(1)
                    .maxPlayers(2)
                    .gameHref("/ticTacToe")
                    .build();
            gameTypeRepository.save(ticTacToe);
        }
    }

}
