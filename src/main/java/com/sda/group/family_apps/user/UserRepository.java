package com.sda.group.family_apps.user;


import org.springframework.data.jpa.repository.JpaRepository;

//tworzymy interfejs, żeby Spring mógł utworzyć ijego implementację
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
    boolean existsByPasswordHash(String username);



}
