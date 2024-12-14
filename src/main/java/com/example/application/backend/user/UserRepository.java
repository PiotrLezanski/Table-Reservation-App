package com.example.application.backend.user;

import com.example.application.entities.user.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Long>
{
    @Query("select u from CustomUser u " +
            "where lower(u.username) = lower(:username)")
    Optional<CustomUser> findByUsername(String username);

    @Query("select u from CustomUser u " +
            "where lower(u.email) = lower(:email)")
    Optional<CustomUser> findByEmail(String email);
}