package com.example.application.views;

import com.example.application.globals.Globals;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.*;

@Route("login")
@PageTitle("Login | Table Reservation App")
public class LoginView extends LoginOverlay implements AfterNavigationObserver, BeforeEnterObserver
{
    public LoginView()
    {
        addClassName("login-view");
        initializeLoginView();
    }
    
    private void initializeLoginView()
    {
        var loginForm = LoginI18n.createDefault();

        loginForm.setHeader(new LoginI18n.Header());
        loginForm.getHeader().setTitle("Welcome to " + Globals.appName + "!");

        loginForm.setForm(new LoginI18n.Form());
        loginForm.getForm().setSubmit("Sign in");
        loginForm.getForm().setTitle("Sign in");
        loginForm.getForm().setUsername("Email");
        loginForm.getForm().setPassword("Password");

        loginForm.getErrorMessage().setTitle("Auth.ErrorTitle");
        loginForm.getErrorMessage().setMessage("Auth.ErrorMessage");

        loginForm.getForm().setForgotPassword("Create Account");
        setForgotPasswordButtonVisible(true);
    
        addForgotPasswordListener(forgotPasswordEvent -> createAccountButtonClicked());
        
        setI18n(loginForm);
        setAction("login");
//        setOpened(true);
    }
    
    private void createAccountButtonClicked()
    {
        UI.getCurrent().navigate(UserPickView.class);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event)
    {
        setOpened(true);
    }
    
    @Override
    public void afterNavigation(AfterNavigationEvent event)
    {
        setError(event.getLocation().getQueryParameters().getParameters().containsKey("error"));
    }
    
    private LoginI18n loginForm;
}
