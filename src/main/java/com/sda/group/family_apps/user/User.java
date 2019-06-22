package com.sda.group.family_apps.user;

import com.sda.group.family_apps.BaseEntity;
import com.sda.group.family_apps.roles.Role;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    @Embedded
    private UserAddress userAddress;
    private String birthDate;
    private String pesel;
    private String username;
    private String passwordHash;
    private String phone;
    private boolean preferEmails;
    @ManyToMany
    @JoinTable(name = "users_roles")
    private Set<Role> roles = new HashSet<>();

}
