package com.example.application.UI.owner.restaurants;

import com.example.application.UI.common.IView;
import com.example.application.UI.owner.homepage.OMainMenuView;
import com.example.application.backend.RestaurantService;
import com.example.application.entities.Restaurant;
import com.example.application.globals.Globals;
import com.example.application.globals.RestaurantType;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("add restaurant")
@Route(value = "add-restaurant", layout = OMainMenuView.class)
@RolesAllowed(Globals.ROLE_OWNER)
public class AddRestaurantView extends VerticalLayout implements IView
{
    public AddRestaurantView(RestaurantService restaurantService)
    {
        this.restaurantService = restaurantService;
        configureUI();
        initializeView();
    }

    @Override
    public void initializeView()
    {
        H1 title = new H1("Add a new restaurant");
        
        add(
                title,
                new HorizontalLayout(
                        name,
                        address,
                        city
                ),
                
                description,
                restaurantTypeComboBox,
                
                new HorizontalLayout(
                        addRestaurantButton,
                        clearButton
                )
        );
    }
    
    private void configureButtons()
    {
        addRestaurantButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
                
        clearButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY,
                                        ButtonVariant.LUMO_ERROR);
    }
    
    private void configureComboBox()
    {
        restaurantTypeComboBox.setItems(RestaurantType.values());
        restaurantTypeComboBox.setPlaceholder("Select type");
    }
    
    @Override
    public void configureUI() 
    {
//        setAlignItems(Alignment.CENTER);
//        setJustifyContentMode(JustifyContentMode.CENTER);

        configureButtons();
        configureComboBox();
        
        description.setWidth("610px");
        description.setMaxHeight("90px");
    }
    
    private void addRestaurantButtonClicked(ClickEvent<Button> buttonClickEvent)
    {
        if(checkTextFieldEmpty())
        {
            Globals.showPopup("Please fill in all the fields", NotificationVariant.LUMO_ERROR, Notification.Position.BOTTOM_CENTER);
            return;
        }

        Restaurant newRestaurant = createRestaurantFromTextFields();
        restaurantService.save(newRestaurant);
        Globals.showPopup("New restaurant added", NotificationVariant.LUMO_SUCCESS, Notification.Position.BOTTOM_CENTER);
        clearTextFields();
    }

    private boolean checkTextFieldEmpty()
    {
        if(name.isEmpty() || address.isEmpty() || city.isEmpty() || description.isEmpty() || restaurantTypeComboBox.isEmpty())
        {
            return true;
        }
        return false;
    }

    private Restaurant createRestaurantFromTextFields() 
    {
        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setName(makeCapital(name.getValue()));
        newRestaurant.setAddress(makeCapital(address.getValue()));
        newRestaurant.setCity(makeCapital(city.getValue()));
        newRestaurant.setDescription(description.getValue());
        newRestaurant.setType(restaurantTypeComboBox.getValue());
        newRestaurant.setImageUrl("");
        
        return newRestaurant;
    }

    private void clearButtonClicked(ClickEvent<Button> buttonClickEvent) 
    {
        clearTextFields();
    }
    
    private void clearTextFields()
    {
        name.clear();
        address.clear();
        city.clear();
        description.clear();
        restaurantTypeComboBox.clear();
    }
    
    private String makeCapital(String text)
    {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
    
    private RestaurantService restaurantService;
    
    private TextField name = new TextField("Name");
    private TextField address = new TextField("Address");
    private TextField city = new TextField("City");
    private TextArea description = new TextArea("Description");
    ComboBox<RestaurantType> restaurantTypeComboBox = new ComboBox<>("Type");
    
    private Button addRestaurantButton = new Button("Add restaurant", this::addRestaurantButtonClicked);
    private Button clearButton = new Button("Clear", this::clearButtonClicked);
}
