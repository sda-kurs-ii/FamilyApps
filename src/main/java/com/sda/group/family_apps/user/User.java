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

    private String username;
    private String passwordHash;
    @Embedded
    private String birthDate;
    private String preferEmails;
    private UserAddress userAddress;
    @ManyToMany
    @JoinTable(name = "users_roles")
    private Set<Role> roles = new HashSet<>();
}
