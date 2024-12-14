package com.example.application.UI.customer.restaurants;

import com.example.application.UI.common.IView;
import com.example.application.UI.customer.homepage.CMainMenuView;
import com.example.application.backend.restaurant.RestaurantService;
import com.example.application.entities.restaurant.Restaurant;
import com.example.application.globals.Globals;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.context.annotation.Scope;

@org.springframework.stereotype.Component
@Scope("prototype")
@PageTitle("restaurants")
@Route(value = "restaurants", layout = CMainMenuView.class)
@RolesAllowed(Globals.ROLE_CUSTOMER)
public class RestaurantListView extends VerticalLayout implements IView
{
    public RestaurantListView(RestaurantService restaurantService)
    {
        this.restaurantService = restaurantService;
        configureUI();
        initializeView();
        updateList();
        closeReservationForm();
        
        add(
                new HorizontalLayout(
                        filteredByNameTextField,
                        filteredByCityTextField
                ),
                createGridFormComponent()
        );
    }

    @Override
    public void configureUI()
    {
        setSizeFull();
        configureGrid();
        configureForm();
    }

    private void configureGrid()
    {
        grid.setSizeFull();
        grid.setColumns("id", "name", "city", "address", "type");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        
        prev = null;
        grid.addItemClickListener(e -> openCloseReservationForm(e.getItem()));
    }

    private void configureForm() 
    {
        reservationForm.addMakeReservationListener(this::makeReservation);
        reservationForm.addCancelReservationListener(this::cancelReservation);
        reservationForm.addCloseListener(e -> closeReservationForm());
    }

    private void makeReservation(ReservationForm.MakeReservationEvent makeReservationEvent) 
    {
        
    }

    private void cancelReservation(ReservationForm.CancelReservationEvent cancelReservationEvent) 
    {
        
    }

    private void openCloseReservationForm(Restaurant current)
    {
        if(current == prev)
        {
            closeReservationForm();
        }
        else
        {
            prev = current;
            reservationForm.fillWithRestaurant(current);
            reservationForm.setVisible(true);
            addClassName("editing");
        }
    }

    @Override
    public void initializeView()
    {
        initializeFilterTextFields();
        initializeReservationForm();
    }

    private void updateList()
    {
        grid.setItems(restaurantService.findAll());
        updateImages();
    }

    private void updateImages() 
    {
        grid.addComponentColumn(restaurant -> {
            Image image = new Image();
            image.setSrc(restaurant.getImageUrl());
            image.setWidth("150px");
            image.setHeight("150px");

            return image;
        });
    }

    private void initializeFilterTextFields()
    {
        filteredByNameTextField.setPlaceholder("Filter by name...");
        filteredByNameTextField.setClearButtonVisible(true);
        filteredByNameTextField.setValueChangeMode(ValueChangeMode.LAZY);
        filteredByNameTextField.addValueChangeListener(e -> filterByNameTextChanged());

        filteredByCityTextField.setPlaceholder("Filter by city...");
        filteredByCityTextField.setClearButtonVisible(true);
        filteredByCityTextField.setValueChangeMode(ValueChangeMode.LAZY);
        filteredByCityTextField.addValueChangeListener(e -> filterByCityTextChanged());
    }

    private void filterByNameTextChanged()
    {
        grid.setItems(restaurantService.findAllByName(filteredByNameTextField.getValue()));
    }

    private void filterByCityTextChanged()
    {
        grid.setItems(restaurantService.findAllByCity(filteredByCityTextField.getValue()));
    }
    
    private void initializeReservationForm()
    {
        reservationForm = new ReservationForm();
        reservationForm.setWidth("25em");
    }
        
    private Component createGridFormComponent()
    {
        HorizontalLayout gridFormLayout = new HorizontalLayout(grid, reservationForm);
        gridFormLayout.setFlexGrow(2, grid);
        gridFormLayout.setFlexGrow(1, reservationForm);
        gridFormLayout.setSizeFull();
        
        return gridFormLayout;
    }

    private void closeReservationForm()
    {
        prev = null;
        reservationForm.fillWithRestaurant(null);
        reservationForm.setVisible(false);
        reservationForm.removeClassName("editing");
    }

    public Grid<Restaurant> getGrid() {
        return grid;
    }

    public TextField getFilteredByNameTextField() {
        return filteredByNameTextField;
    }

    public TextField getFilteredByCityTextField() {
        return filteredByCityTextField;
    }

    public ReservationForm getReservationForm() {
        return reservationForm;
    }

    private Grid<Restaurant> grid = new Grid<>(Restaurant.class);
    private RestaurantService restaurantService;
    private TextField filteredByNameTextField = new TextField();
    private TextField filteredByCityTextField = new TextField();
    
    private ReservationForm reservationForm = new ReservationForm();
    Restaurant prev;
}
