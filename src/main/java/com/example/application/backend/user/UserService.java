package com.example.application.backend.user;

import com.example.application.entities.user.CustomUser;
import com.example.application.globals.exceptions.EmailAlreadyExistsException;
import com.example.application.globals.exceptions.UsernameAlreadyExistsException;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static com.example.application.globals.Globals.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService
{
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<CustomUser> customUser = userRepository.findByUsername(username);
        if(customUser.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return org.springframework.security.core.userdetails.User.builder()
                .username(customUser.get().getUsername())
                .password(customUser.get().getPassword()) // Ensure it's hashed
                .roles(customUser.get().getRole())        // Set roles
                .build();
    }

    public void save(CustomUser user)
    {
        if(user == null)
            throw new IllegalArgumentException("Cannot save a null user.");

        Optional<CustomUser> alreadyFoundUser;
        alreadyFoundUser = userRepository.findByUsername(user.getUsername());
        if(alreadyFoundUser.isPresent())
            throw new UsernameAlreadyExistsException("User with username '" + user.getUsername() + "' already exists.");

        alreadyFoundUser = userRepository.findByEmail(user.getEmail());
        if(alreadyFoundUser.isPresent())
            throw new EmailAlreadyExistsException("User with email '" + user.getEmail() + "' already exists.");

        // Email and username needs to be unique
        userRepository.save(user);
    }

    // check if user with given username and password can log in
    public boolean authenticate(String username, String password)
    {
        Optional<CustomUser> user = userRepository.findByUsername(username);
        return user.filter(customUser -> password.equals(customUser.getPassword())).isPresent();
    }

    @PostConstruct
    public void populateTestData()
    {
        if(userRepository.count() == 0)
            userRepository.saveAll(generateTestData());
    }

    private List<CustomUser> generateTestData()
    {
        List<CustomUser> users = new ArrayList<>();
        users.add(new CustomUser("user", passwordEncoder.encode("userpass"), "user@a.pl", ROLE_CUSTOMER));
        users.add(new CustomUser("admin", passwordEncoder.encode("adminpass"), "owner@a.pl", ROLE_OWNER));
        return users;
    }

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;
}
