package com.example.application.views.login;

import com.example.application.Globals;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("customer-signup")
@PageTitle("Customer SignUp | Table Reservation App")
public class CustomerSignUpView extends VerticalLayout
{
    public CustomerSignUpView()
    {
        // Create a form layout
        loginForm = new FormLayout();

        initializeForm();
        configureUI();
        
        // Add form layout to the main layout
        add(loginForm);
    }

    private void configureUI()
    {
        loginForm.setMaxWidth("400px");
        
        setSizeFull();

        // Set vertical alignment to center
        setAlignItems(Alignment.CENTER);

        // Set the form layout alignment to center
        loginForm.setMaxWidth("400px");
        loginForm.getStyle().set("margin", "auto");
        loginForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
    }

    private void initializeForm()
    {
        H1 title = new H1("Sign Up");
        usernameField = new TextField("Username");
        passwordField = new PasswordField("Password");
        repeatPasswordField = new PasswordField("Repeat Password");
        loginButton = new Button("Sign Up", event -> attemptSignUp(usernameField.getValue(), passwordField.getValue()));

        loginForm.add(
                title, 
                usernameField, 
                passwordField,
                repeatPasswordField,
                loginButton
        );
    }

    private void attemptSignUp(String username, String password) {
        // Implement your authentication logic here
        if ("user".equals(username) && "password".equals(password)) {
            // Successful login
            Notification.show("Login successful", 2000, Notification.Position.BOTTOM_CENTER);
            // Redirect to another view after successful login
            getUI().ifPresent(ui -> ui.navigate("dashboard"));
        } else {
            // Failed login
            Globals.showPopup("Invalid username or password", NotificationVariant.LUMO_CONTRAST, Notification.Position.BOTTOM_CENTER);
        }
    }
    
    private FormLayout loginForm;
    private TextField usernameField;
    private PasswordField passwordField;
    private PasswordField repeatPasswordField;
    private Button loginButton;
}
