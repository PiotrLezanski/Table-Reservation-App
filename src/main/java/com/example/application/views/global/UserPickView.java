package com.example.application.views.global;

import com.example.application.globals.Globals;
import com.example.application.globals.UserType;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import static com.example.application.globals.UserType.CUSTOMER;
import static com.example.application.globals.UserType.RESTAURANT_OWNER;

@Route("user-pick")
@PageTitle("User Pick | Table Reservation App")
@AnonymousAllowed
public class UserPickView extends VerticalLayout {
    
    static final String customerName = "Customer";
    static final String restaurantOwner = "Restaurant Owner";
    
    public UserPickView() 
    {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        
        initializeView();
    }
    
    private void initializeView()
    {
        initializeUserTypeCheckbox();
        initializeButtons();
        
        add(
                userTypeButtons,
                new HorizontalLayout(backButton, goButton)
        );
    }
    
    private void initializeButtons()
    {
        backButton = new Button("Back", new Icon(VaadinIcon.ARROW_LEFT), this::backButtonClicked);
        backButton.setIconAfterText(false);
        backButton.setAutofocus(false);
        backButton.setClassName("white_background_button");
        
        goButton = new Button("Go", new Icon(VaadinIcon.ARROW_RIGHT), this::goButtonClicked);
        goButton.setIconAfterText(true);
        goButton.setAutofocus(true);
        goButton.setClassName("white_background_button");
    }

    private void backButtonClicked(ClickEvent<Button> buttonClickEvent)
    {
        UI.getCurrent().navigate(LoginView.class);
    }

    private void goButtonClicked(ClickEvent<Button> clicked)
    {
        SignUpViewFactory signUpViewFactory = new SignUpViewFactory();
        switch(userType)
        {
            case CUSTOMER -> UI.getCurrent().navigate(signUpViewFactory.createCustomerSignUpView().getClass()); 
            case RESTAURANT_OWNER -> UI.getCurrent().navigate(signUpViewFactory.createOwnerSignUpView().getClass());
            default -> Globals.showPopup("User type not valid", NotificationVariant.LUMO_ERROR, Notification.Position.BOTTOM_CENTER);
        }
    }

    private void initializeUserTypeCheckbox()
    {
        userTypeButtons = new RadioButtonGroup<>();
        userTypeButtons.setLabel("Hi, who are you?");
        userTypeButtons.setItems(customerName, restaurantOwner);
        userTypeButtons.setValue(customerName);
        
        userTypeButtons.addValueChangeListener(event -> {
                    userType = event.getValue().equals(customerName) ? CUSTOMER : RESTAURANT_OWNER;
                }
        );
    }

    private RadioButtonGroup<String> userTypeButtons;
    private Button backButton;
    private Button goButton;
    private UserType userType = CUSTOMER;
}



