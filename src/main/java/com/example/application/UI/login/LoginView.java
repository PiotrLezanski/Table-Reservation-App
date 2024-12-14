package com.example.application.UI.login;

import com.example.application.UI.common.IView;
import com.example.application.UI.signup.UserPickView;
import com.example.application.backend.user.UserService;
import com.example.application.globals.Globals;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route("login")
@PageTitle("Login | Table Reservation App")
public class LoginView extends LoginOverlay implements IView, AfterNavigationObserver, BeforeEnterObserver
{
    @Autowired
    public LoginView(UserService userService)
    {
        this.userService = userService;
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
        loginForm = LoginI18n.createDefault();

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
        addLoginListener(this::signInButtonClicked);

        setI18n(loginForm);
    }
    
    private void createAccountButtonClicked()
    {
        UI.getCurrent().navigate(UserPickView.class);
    }

    private void signInButtonClicked(LoginEvent event)
    {
        String username = event.getUsername();
        String password = event.getPassword();

        // Authenticate the user (you can use your existing service for this)
        boolean authenticated = userService.authenticate(username, password);

        if (authenticated)
        {
            // Successful login, navigate to the appropriate view
            UI.getCurrent().getPage().setLocation("");
        }
        else
        {
            // Failed login, show an error
            setError(true);
        }
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

    public LoginI18n getLoginForm()
    {
        return loginForm;
    }

    private LoginI18n loginForm;
    private final UserService userService;
}
