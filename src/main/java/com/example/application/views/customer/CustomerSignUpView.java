package com.example.application.views.customer;

import com.example.application.globals.Globals;
import com.example.application.views.global.ISignUpView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("customer-signup")
@PageTitle("Customer SignUp | Table Reservation App")
@AnonymousAllowed
public class CustomerSignUpView extends VerticalLayout implements ISignUpView
{
    public CustomerSignUpView()
    {
        // Create a form layout
        formLayout = new FormLayout();

        initializeForm();
        configureUI();
        
        // Add form layout to the main layout
        add(formLayout);
    }

    @Override
    public void initializeForm()
    {
        initializeBasicDataForm();

        formLayout.add(
                signupTitle, 
                usernameField,
                emailField,
                passwordField,
                repeatPasswordField,
                signUpButton,
                backButton
        );
    }
    
    private void initializeBasicDataForm()
    {
        signupTitle = new H1("Customer Sign Up");
        signupTitle.getStyle().set("margin-top", "20px");

        usernameField = new TextField("Username");
        
        emailField = new EmailField("Email Address");
        emailField.setPlaceholder("your_mail@example.com");
        
        passwordField = new PasswordField("Password");
        repeatPasswordField = new PasswordField("Repeat Password");
        
        signUpButton = new Button("Sign Up", this::attemptSignUp);
        signUpButton.getStyle().set("margin-top", "20px");

        backButton = new Button("back", new Icon(VaadinIcon.ARROW_LEFT), this::backButtonClicked);
        backButton.setClassName("transparent_background_button");
    }

    private void configureUI()
    {
        formLayout.setMaxWidth("400px");

        setSizeFull();

        // Set vertical alignment to center
        setAlignItems(Alignment.CENTER);

        // Set the form layout alignment to center
        formLayout.setMaxWidth("400px");
        formLayout.getStyle().set("margin", "auto");
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
    }
    
    @Override
    public void attemptSignUp(ClickEvent<Button> clicked)
    {
        // TODO: Implement your authentication logic here, add new customer
        Globals.showPopup("New customer signed up", NotificationVariant.LUMO_SUCCESS, Notification.Position.BOTTOM_CENTER);
    }
    
    private final FormLayout formLayout;
    private H1 signupTitle;
    private TextField usernameField;
    private EmailField emailField;
    private PasswordField passwordField;
    private PasswordField repeatPasswordField;
    private Button signUpButton;
    private Button backButton;
}
