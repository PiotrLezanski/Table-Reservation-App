package com.example.application.UI.customer.signup;

import com.example.application.UI.common.ISignUpView;
import com.example.application.UI.common.IView;
import com.example.application.UI.login.LoginView;
import com.example.application.backend.user.UserService;
import com.example.application.entities.user.CustomUser;
import com.example.application.globals.Globals;
import com.example.application.globals.exceptions.EmailAlreadyExistsException;
import com.example.application.globals.exceptions.UsernameAlreadyExistsException;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.application.globals.Globals.ROLE_CUSTOMER;

@Route("customer-signup")
@PageTitle("Customer SignUp | Table Reservation App")
@AnonymousAllowed
public class CSignUpView extends VerticalLayout implements ISignUpView, IView
{
    @Autowired
    public CSignUpView(UserService userService)
    {
        this.userService = userService;

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
        initializeView();

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

    @Override
    public void initializeView() 
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
        backButton.setClassName("transparent-background-button");
    }

    @Override
    public void configureUI()
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
        if(usernameField.isEmpty() || emailField.isEmpty() || passwordField.isEmpty() || repeatPasswordField.isEmpty())
        {
            Globals.showPopup("None of the fields can be empty", NotificationVariant.LUMO_ERROR, Notification.Position.BOTTOM_CENTER);
            return;
        }

        if(!passwordField.getValue().equals(repeatPasswordField.getValue()))
        {
            Globals.showPopup("Password is not the same", NotificationVariant.LUMO_ERROR, Notification.Position.BOTTOM_CENTER);
            return;
        }

        CustomUser newUser = new CustomUser(
                usernameField.getValue(), passwordEncoder.encode(passwordField.getValue()), emailField.getValue(), ROLE_CUSTOMER
        );

        try
        {
            userService.save(newUser);
            Globals.showPopup("New customer signed up", NotificationVariant.LUMO_SUCCESS, Notification.Position.BOTTOM_CENTER);
            UI.getCurrent().navigate(LoginView.class);
        }
        catch(UsernameAlreadyExistsException e)
        {
            Globals.showPopup("User with this username already exists", NotificationVariant.LUMO_ERROR, Notification.Position.BOTTOM_CENTER);
        }
        catch(EmailAlreadyExistsException e)
        {
            Globals.showPopup("User with this email already exists", NotificationVariant.LUMO_ERROR, Notification.Position.BOTTOM_CENTER);
        }
    }

    private final UserService userService;
    private final FormLayout formLayout;
    private H1 signupTitle;
    private TextField usernameField;
    private EmailField emailField;
    private PasswordField passwordField;
    private PasswordField repeatPasswordField;
    private Button signUpButton;
    private Button backButton;
}
