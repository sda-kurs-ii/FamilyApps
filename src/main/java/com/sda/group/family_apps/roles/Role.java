package com.sda.group.family_apps.roles;

import com.sda.group.family_apps.BaseEntity;
import lombok.NoArgsConstructor;

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
