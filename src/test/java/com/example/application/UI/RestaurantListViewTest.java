package com.example.application.UI;

import com.example.application.UI.customer.ReservationForm;
import com.example.application.UI.customer.RestaurantListView;
import com.example.application.backend.Restaurant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RestaurantListViewTest
{
    @BeforeEach
    public void setUp()
    {
        grid = restaurantListView.getGrid();
    }
    
    @Test
    public void testFilterByValidName()
    {
        String validName = getFirstItem().getName();

        restaurantListView.getFilteredByNameTextField().setValue(validName);
        Collection<Restaurant> restaurants = getRestaurantList();
        
        restaurants.forEach(restaurant -> {assertEquals(validName, restaurant.getName());});
    }

    private Collection<Restaurant> getRestaurantList()
    {
        grid = restaurantListView.getGrid();
        return ((ListDataProvider<Restaurant>)grid.getDataProvider()).getItems();
    }
    
    private Restaurant getFirstItem()
    {
        return getRestaurantList().iterator().next();
    }

    @Test
    public void testFilterByInvalidName()
    {
        String validName = "_Invalid_Name_";

        restaurantListView.getFilteredByNameTextField().setValue(validName);
        Collection<Restaurant> restaurants = getRestaurantList();
        
        assertEquals(0, restaurants.size());
    }
    
    @Test
    public void testFilterByValidCity()
    {
        String validCity = getFirstItem().getCity();

        restaurantListView.getFilteredByCityTextField().setValue(validCity);
        Collection<Restaurant> restaurants = getRestaurantList();

        restaurants.forEach(restaurant -> {assertEquals(validCity, restaurant.getCity());});
    }

    @Test
    public void testFilterByInvalidCity()
    {
        String validCity = "_Invalid_City_";

        restaurantListView.getFilteredByCityTextField().setValue(validCity);
        Collection<Restaurant> restaurants = getRestaurantList();

        assertEquals(0, restaurants.size());
    }

    @Test
    public void testFormInvisibleByDefault()
    {
        ReservationForm form = restaurantListView.getReservationForm();
        assertFalse(form.isVisible());
    }
    
    @Test
    public void testFormVisibleWhenRestaurantSelected()
    {
        ReservationForm form = restaurantListView.getReservationForm();
        Restaurant firstRestaurant = getFirstItem();
        
        grid.asSingleSelect().setValue(firstRestaurant);
        
        assertTrue(form.isVisible());
        assertEquals(firstRestaurant.getName(), form.getName().getValue());
    }
    
    @Autowired
    private RestaurantListView restaurantListView;
    private Grid<Restaurant> grid;
}
