package com.example.application.UI.customer;

import com.example.application.UI.IView;
import com.example.application.backend.Restaurant;
import com.example.application.backend.RestaurantService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("restaurants")
@Route(value = "restaurants", layout = MainMenuView.class)
@PermitAll
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
    }
    
    private void configureGrid()
    {
        grid.setSizeFull();
        grid.setColumns("name", "city", "address", "type");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        
        prev = null;
        grid.addItemClickListener(e -> openCloseReservationForm(e.getItem()));
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

    private Grid<Restaurant> grid = new Grid<>(Restaurant.class);
    private RestaurantService restaurantService;
    private TextField filteredByNameTextField = new TextField();
    private TextField filteredByCityTextField = new TextField();
    
    private ReservationForm reservationForm;
    Restaurant prev;
}
