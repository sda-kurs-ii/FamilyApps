package com.sda.group.family_apps.user;

import com.google.common.collect.Sets;
import com.sda.group.family_apps.roles.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserRegistrationService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void registerUser(UserRegistrationDTO dto) {
        User user = dtoToEntity(dto);
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("User with email: "
                    + user.getUsername() + " exists");
        } else {
            userRepository.save(user);
        }
    }

    private User dtoToEntity(UserRegistrationDTO dto) {
        UserAddress address = UserAddress.builder()
                .city(dto.getCity())
                .country(dto.getCountry())
                .street(dto.getStreet())
                .zipCode(dto.getZipCode())
                .build();

        return User.builder()
                .username(dto.getUsername())
                .birthDate(dto.getBirthDate())
                .preferEmails(dto.getPreferEmails())
                .userAddress(address)
                .roles(Sets.newHashSet(
                        roleRepository.findByRoleName("ROLE_USER")))
                .passwordHash(encoder.encode(dto.getPassword()))
                .build();

    }
}
