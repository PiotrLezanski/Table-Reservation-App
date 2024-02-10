package com.example.application.UI.owner;

import com.example.application.UI.common.IView;
import com.example.application.globals.Globals;
import com.example.application.UI.common.ISignUpView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
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

@Route("owner-signup")
@PageTitle("Restaurant Owner SignUp | Table Reservation App")
@AnonymousAllowed
public class OwnerSignUpView extends VerticalLayout implements ISignUpView, IView
{
    public OwnerSignUpView()
    {
        // Create a form layout
        formLayout = new FormLayout();

        configureUI();
        initializeForm();

        // Add form layout to the main layout
        add(formLayout);
    }

    @Override
    public void configureUI()
    {
        formLayout.setMinWidth("400px");

        setSizeFull();

        // Set vertical alignment to center
        setAlignItems(Alignment.CENTER);

        // Set the form layout alignment to center
        formLayout.setMaxWidth("400px");
        formLayout.getStyle().set("margin", "auto");
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
    }

    @Override
    public void initializeView()
    {
        signupTitle = new H1("Restaurant Sign Up");
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
    public void initializeForm()
    {
        initializeView();
        initializeRestaurantInfoForm();
        
        formLayout.add(
                signupTitle,
                usernameField,
                emailField,
                passwordField,
                repeatPasswordField,
                
                restaurantInfoTitle,
                restaurantNameField,
                restaurantAddressField,
                
                signUpButton,
                backButton
        );
    }
    
    private void initializeRestaurantInfoForm()
    {
        restaurantInfoTitle = new H3("Restaurant info");
        restaurantInfoTitle.getStyle().set("margin-top", "20px");
        
        restaurantNameField = new TextField("Restaurant Name");
        restaurantAddressField = new TextField("Address");
    }

    @Override
    public void attemptSignUp(ClickEvent<Button> clicked)
    {
        // TODO: Implement your authentication logic here
        Globals.showPopup("New restaurant signed up", NotificationVariant.LUMO_SUCCESS, Notification.Position.BOTTOM_CENTER);
    }
    
    private H1 signupTitle;
    private final FormLayout formLayout;
    private TextField usernameField;
    private EmailField emailField;
    private PasswordField passwordField;
    private PasswordField repeatPasswordField;
    
    private H3 restaurantInfoTitle;
    private TextField restaurantNameField;
    private TextField restaurantAddressField;
    
    private Button signUpButton;
    private Button backButton;
}
