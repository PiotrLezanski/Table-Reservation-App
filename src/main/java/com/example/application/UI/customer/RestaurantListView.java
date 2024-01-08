package com.example.application.UI.customer;

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
public class RestaurantListView extends VerticalLayout
{
    public RestaurantListView(RestaurantService restaurantService)
    {
        this.restaurantService = restaurantService;
        configureUI();
        initializeView();
        updateList();
        closeReservationForm();
        
        add(
                filteredText,
                createGridFormComponent()
        );
    }

    private void configureUI()
    {
        setSizeFull();
        configureGrid();
    }
    
    private void configureGrid()
    {
        grid.setSizeFull();
        grid.setColumns("name", "address", "type");
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

    private void initializeView()
    {
        //addImageColumn();
        initializeFilterText();
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

    private void initializeFilterText()
    {
        filteredText.setPlaceholder("Filter by name...");
        filteredText.setClearButtonVisible(true);
        filteredText.setValueChangeMode(ValueChangeMode.LAZY);
        filteredText.addValueChangeListener(e -> filterTextChanged());
    }

    private void filterTextChanged()
    {
        grid.setItems(restaurantService.findAll(filteredText.getValue()));
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
    private TextField filteredText = new TextField();
    
    private ReservationForm reservationForm;
    Restaurant prev;
}
