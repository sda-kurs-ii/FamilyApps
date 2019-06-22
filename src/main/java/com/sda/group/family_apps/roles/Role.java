package com.sda.group.family_apps.roles;

import lombok.NoArgsConstructor;
import pl.sda.springtraining.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "ROLES")
public class Role extends BaseEntity {

    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
