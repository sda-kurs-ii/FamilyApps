package com.sda.group.family_apps.user;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    boolean existsByPasswordHash(String username);

    User findUserByUsername(String username);
}
