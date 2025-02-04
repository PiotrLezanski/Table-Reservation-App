package com.example.application.UI.common;

import com.example.application.UI.signup.UserPickView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface ISignUpView
{
    void initializeForm();
    void attemptSignUp(ClickEvent<Button> clicked);

    default void backButtonClicked(ClickEvent<Button> clicked)
    {
        UI.getCurrent().navigate(UserPickView.class);
    }

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
}
