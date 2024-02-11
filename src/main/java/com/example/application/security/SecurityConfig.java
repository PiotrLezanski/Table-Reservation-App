package com.example.application.security;

import com.example.application.UI.login.LoginView;
import com.example.application.globals.UserType;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity
{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Override
    protected void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
    
    @Bean
    protected UserDetailsManager userDetailsManager() 
    {
        UserDetails user = User.withUsername("user")
                .password("{noop}userpass")
                .roles(UserType.CUSTOMER.toString())
                .build();

        UserDetails admin = User.withUsername("owner")
                .password("{noop}ownerpass")
                .roles(UserType.OWNER.toString())
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
