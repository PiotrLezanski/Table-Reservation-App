package com.example.application.UI.customer;

import com.example.application.backend.Restaurant;
import com.example.application.globals.RestaurantType;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.*;
import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.shared.Registration;

import java.time.Duration;
import java.time.LocalTime;

public class ReservationForm extends FormLayout
{
    public ReservationForm()
    {
        binder.forField(type)
                .withConverter(new EnumToStringConverter<>(RestaurantType.class))
                .bind("type");
        binder.bindInstanceFields(this);
        
        configureDateAndTimePicker();
        configureButtons();
        
        HorizontalLayout timeLayout = new HorizontalLayout(datePicker, timePicker);
        
        HorizontalLayout buttonsLayout = new HorizontalLayout(makeReservationButton,
                                                            cancelReservationButton,
                                                            closeButton);
        buttonsLayout.getStyle().set("margin-top", "20px");
        
        configureUI();
        add(
                text,
                name,
                description,
                address,
                type,
                timeLayout,
                buttonsLayout
        );
    }
    
    private void configureUI()
    {
        text.getStyle().set("color", "#39ab82");
        
        name.setReadOnly(true);
        description.setReadOnly(true);
        address.setReadOnly(true);
        type.setReadOnly(true);
    }
    
    private void configureButtons()
    {
        makeReservationButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        makeReservationButton.addClickListener(event -> makeReservationButtonClicked());
        
        cancelReservationButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        // the button will be enabled after reservation is made
        cancelReservationButton.setEnabled(false);
        cancelReservationButton.addClickListener(event -> cancelReservationButtonClicked());
        
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        closeButton.addClickListener(event -> fireEvent(new CloseEvent(this)));
    }

    private void makeReservationButtonClicked()
    {
        try {
            binder.writeBean(currentRestaurant);
            fireEvent(new MakeReservationEvent(this, currentRestaurant));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    private void cancelReservationButtonClicked()
    {
        
    }
    
    private void configureDateAndTimePicker()
    {
        datePicker = new DatePicker("Date");

        timePicker = new TimePicker();
        timePicker.setLabel("Time");
        timePicker.setStep(Duration.ofMinutes(15));
        timePicker.setValue(LocalTime.of(7, 0));
    }
    
    public void fillWithRestaurant(Restaurant restaurant)
    {
        currentRestaurant = restaurant;
        binder.readBean(restaurant);
    }
    
    private H2 text = new H2("Book your table here");
    private TextField name = new TextField("Name");
    private TextField description = new TextField("Description");
    private TextField address = new TextField("Address");
    private TextField type = new TextField("Type");
    
    private DatePicker datePicker;
    private TimePicker timePicker;
    
    private Button makeReservationButton = new Button("Make a reservation");
    private Button cancelReservationButton = new Button("Cancel reservation");
    private Button closeButton = new Button("Close");
    
    private Restaurant currentRestaurant;
    private Binder<Restaurant> binder = new BeanValidationBinder(Restaurant.class);

    
    // Events
    public static abstract class ReservationFormEvent extends ComponentEvent<ReservationForm> {
        private Restaurant restaurant;

        protected ReservationFormEvent(ReservationForm source, Restaurant restaurant) {
            super(source, false);
            this.restaurant = restaurant;
        }

        public Restaurant getRestaurant() {
            return restaurant;
        }
    }

    public static class MakeReservationEvent extends ReservationFormEvent {
        MakeReservationEvent(ReservationForm source, Restaurant restaurant) {
            super(source, restaurant);
        }
    }

    public static class CancelReservationEvent extends ReservationFormEvent {
        CancelReservationEvent(ReservationForm source, Restaurant restaurant) {
            super(source, restaurant);
        }

    }

    public static class CloseEvent extends ReservationFormEvent {
        CloseEvent(ReservationForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<CancelReservationEvent> listener) {
        return addListener(CancelReservationEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<MakeReservationEvent> listener) {
        return addListener(MakeReservationEvent.class, listener);
    }
    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}

class EnumToStringConverter<T extends Enum<T>> implements Converter<String, T> {

    private final Class<T> enumType;

    public EnumToStringConverter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public Result<T> convertToModel(String value, ValueContext context) {
        try {
            return Result.ok(Enum.valueOf(enumType, value));
        } catch (IllegalArgumentException e) {
            return Result.error("Invalid value");
        }
    }

    @Override
    public String convertToPresentation(T value, ValueContext context) {
        return value != null ? value.name() : null;
    }
}
