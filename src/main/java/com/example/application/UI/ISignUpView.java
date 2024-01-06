package com.example.application.UI;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;

public interface ISignUpView
{
    void initializeForm();
    void attemptSignUp(ClickEvent<Button> clicked);

    default void backButtonClicked(ClickEvent<Button> clicked)
    {
        UI.getCurrent().navigate(UserPickView.class);
    }
}
