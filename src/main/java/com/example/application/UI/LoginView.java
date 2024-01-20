package com.example.application.UI;

import com.example.application.globals.Globals;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.*;

@Route("login")
@PageTitle("Login | Table Reservation App")
public class LoginView extends LoginOverlay implements IView, AfterNavigationObserver, BeforeEnterObserver
{
    public LoginView()
    {
        configureUI();
        initializeView();
    }

    @Override
    public void configureUI()
    {
        addClassName("login-view");
        setAction("login");
    }
    
    @Override
    public void initializeView()
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
}
