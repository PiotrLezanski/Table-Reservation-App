package com.example.application.views.login;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Login | Table Reservation App")
public class LoginView extends VerticalLayout implements BeforeEnterListener
{
    public LoginView()
    {
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        
        initializeLoginView();
    }
    
    private void initializeLoginView()
    {
        loginForm.setForgotPasswordButtonVisible(true);
        loginForm.setAction("login");
        loginForm.setError(false);
        
        createAccountButton = new Button("Create Account", this::createAccountButtonClicked);
        createAccountButton.addThemeVariants(ButtonVariant.MATERIAL_OUTLINED);
        
        add(
                new H1("Welcome to Table Reservation App!"),
                loginForm,
                createAccountButton
        );
    }
    
    private void createAccountButtonClicked(ClickEvent<Button> clicked)
    {
        loginForm.setVisible(false);
        UI.getCurrent().navigate(UserPickView.class);
    }
    
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent)
    {
        if(beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error"))
        {
            loginForm.setError(true);
        }
    }

    private LoginForm loginForm = new LoginForm();
    private Button createAccountButton;
}
