package com.example.application.UI;

import com.example.application.UI.customer.restaurants.ReservationForm;
import com.example.application.backend.Restaurant;
import com.example.application.globals.RestaurantType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationFormTest
{
    @BeforeEach
    public void setUp()
    {
        reservationForm = new ReservationForm();
    }

    @Test
    void testConfiguringButtons() {
        // Verify that buttons are configured correctly
        assertTrue(reservationForm.getMakeReservationButton().getThemeNames().contains("primary"));
        assertTrue(reservationForm.getCancelReservationButton().getThemeNames().contains("error"));
        assertFalse(reservationForm.getCancelReservationButton().isEnabled());
        assertTrue(reservationForm.getCloseButton().getThemeNames().contains("tertiary"));
    }
    
    @Test
    public void testFillFormWithRestaurant()
    {
        Restaurant restaurant = new Restaurant(
                "Restaurant1",
                "best restaurant in town",
                "Krakow",
                "jana 24/1",
                RestaurantType.Italian,
                ""
        );
        
        reservationForm.fillWithRestaurant(restaurant);

        assertEquals("Restaurant1", reservationForm.getName().getValue());
        assertEquals("best restaurant in town", reservationForm.getDescription().getValue());
        assertEquals("Krakow", reservationForm.getCity().getValue());
        assertEquals("jana 24/1", reservationForm.getAddress().getValue());
        assertEquals("Italian", reservationForm.getType().getValue());
    }
    
    @Test
    void testMakeReservationButtonClicked() {
        reservationForm = new ReservationForm();

        // Simulate a click on makeReservationButton
        reservationForm.getMakeReservationButton().click();
        
        // TODO: add assertion to verify if reservation was made
        assertTrue(true);
    }

    @Test
    void testCancelReservationButtonClicked() {
        reservationForm = new ReservationForm();

        // Simulate a click on cancelReservationButton
        reservationForm.getCancelReservationButton().click();

        // TODO: add assertion to verify if reservation was canceled
        assertTrue(true);
    }
    
    private ReservationForm reservationForm;
}
