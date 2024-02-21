package com.example.application.UI.customer.restaurants;

import com.example.application.UI.common.IView;
import com.example.application.backend.converters.EnumToStringConverter;
import com.example.application.entities.reservation.ReservationInfo;
import com.example.application.entities.restaurant.Restaurant;
import com.example.application.globals.Globals;
import com.example.application.globals.RestaurantType;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.time.Duration;
import java.time.LocalTime;

public class ReservationForm extends FormLayout implements IView
{
    public ReservationForm()
    {
        initializeView();
        configureUI();
    }
    
    @Override
    public void configureUI()
    {
        text.getStyle().set("color", "#39ab82");
        
        name.setReadOnly(true);
        description.setReadOnly(true);
        city.setReadOnly(true);
        address.setReadOnly(true);
        type.setReadOnly(true);
    }

    @Override
    public void initializeView() 
    {
        binder.forField(type)
                .withConverter(new EnumToStringConverter<>(RestaurantType.class))
                .bind("type");
        binder.bindInstanceFields(this);

        configureDateAndTimePicker();
        configureButtons();
        
        configureNumberOfPeopleField();

        HorizontalLayout timeLayout = new HorizontalLayout(datePicker, timePicker);

        configureAdditionalNotesTextArea();

        HorizontalLayout buttonsLayout = new HorizontalLayout(
                makeReservationButton,
                cancelReservationButton,
                closeButton
        );
        buttonsLayout.getStyle().set("margin-top", "20px");
        
        add(
                text,
                name,
                description,
                city,
                address,
                type,
                numberOfPeopleField,
                timeLayout,
                notesTextArea,
                buttonsLayout
        );
    }

    private void configureButtons()
    {
        makeReservationButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        makeReservationButton.addClickListener(this::makeReservationButtonClicked);
        
        cancelReservationButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        // the button will be enabled after reservation is made
        cancelReservationButton.setEnabled(false);
        cancelReservationButton.addClickListener(this::cancelReservationButtonClicked);
        
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        closeButton.addClickListener(event -> fireEvent(new CloseEvent(this)));
    }

    private void configureNumberOfPeopleField()
    {
        numberOfPeopleField.setValue(2);
        numberOfPeopleField.setStepButtonsVisible(true);
        numberOfPeopleField.setMin(1);
        numberOfPeopleField.setMax(10);
    }
    
    private void configureAdditionalNotesTextArea()
    {
        notesTextArea.setMaxHeight("90px");
    }

    private ReservationInfo createReservationInfoFromForm() 
    {
        ReservationInfo reservationInfo = new ReservationInfo();
        reservationInfo.setRestaurantId(currentRestaurant.getId());
        reservationInfo.setNumberOfPeople(numberOfPeopleField.getValue());
        reservationInfo.setDate(datePicker.getValue());
        reservationInfo.setTime(timePicker.getValue());
        return reservationInfo;
    }

    private void makeReservationButtonClicked(ClickEvent<Button> buttonClickEvent)
    {
        if(isAnyFieldEmpty())
        {
            Globals.showPopup("Please fill in all the fields", NotificationVariant.LUMO_ERROR, Notification.Position.BOTTOM_END);
            return;
        }
        
        ReservationInfo reservationInfo = createReservationInfoFromForm();
        fireEvent(new MakeReservationEvent(this, reservationInfo));
        
        cancelReservationButton.setEnabled(true);
//        cancelReservationButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
//                ButtonVariant.LUMO_ERROR);
    }

    private boolean isAnyFieldEmpty() 
    {
        if(numberOfPeopleField.isEmpty() || datePicker.isEmpty() || timePicker.isEmpty())
        {
            return true;
        }
        return false;
    }

    private void cancelReservationButtonClicked(ClickEvent<Button> buttonClickEvent)
    {
        ReservationInfo reservationInfo = createReservationInfoFromForm();
        fireEvent(new CancelReservationEvent(this, reservationInfo));
    }

    private void configureDateAndTimePicker()
    {
        datePicker = new DatePicker("Date");
        datePicker.setRequired(true);

        timePicker = new TimePicker();
        timePicker.setRequired(true);
        timePicker.setValue(null);
        timePicker.setLabel("Time");
        timePicker.setStep(Duration.ofMinutes(15));
        timePicker.setValue(LocalTime.of(7, 0));
    }
    
    public void fillWithRestaurant(Restaurant restaurant)
    {
        currentRestaurant = restaurant;
        binder.readBean(restaurant);
    }

    public TextField getName() {
        return name;
    }

    public TextField getDescription() {
        return description;
    }

    public TextField getCity() {
        return city;
    }

    public TextField getAddress() {
        return address;
    }

    public TextField getType() {
        return type;
    }

    public Button getMakeReservationButton() {
        return makeReservationButton;
    }

    public Button getCancelReservationButton() {
        return cancelReservationButton;
    }

    public Button getCloseButton() {
        return closeButton;
    }
    
    private H2 text = new H2("Book your table here");
    private TextField name = new TextField("Name");
    private TextField description = new TextField("Description");
    private TextField city = new TextField("City");
    private TextField address = new TextField("Address");
    private TextField type = new TextField("Type");
    
    private DatePicker datePicker;
    private TimePicker timePicker;
    
    private IntegerField numberOfPeopleField = new IntegerField("Number of people");
    private TextArea notesTextArea = new TextArea("Additional notes");
    
    private Button makeReservationButton = new Button("Make a reservation");
    private Button cancelReservationButton = new Button("Cancel reservation");
    private Button closeButton = new Button("Close");
    
    private Restaurant currentRestaurant;
    private Binder<Restaurant> binder = new BeanValidationBinder(Restaurant.class);

    
    // Events
    public static abstract class ReservationFormEvent extends ComponentEvent<ReservationForm> {
        private ReservationInfo reservationInfo;

        protected ReservationFormEvent(ReservationForm source, ReservationInfo reservationInfo) {
            super(source, false);
            this.reservationInfo = reservationInfo;
        }

        public ReservationInfo getReservationInfo() {
            return reservationInfo;
        }
    }

    public static class MakeReservationEvent extends ReservationFormEvent {
        MakeReservationEvent(ReservationForm source, ReservationInfo reservationInfo) {
            super(source, reservationInfo);
        }
    }

    public static class CancelReservationEvent extends ReservationFormEvent {
        CancelReservationEvent(ReservationForm source, ReservationInfo reservationInfo) {
            super(source, reservationInfo);
        }
    }

    public static class CloseEvent extends ReservationFormEvent {
        CloseEvent(ReservationForm source) {
            super(source, null);
        }
    }

    public Registration addMakeReservationListener(ComponentEventListener<MakeReservationEvent> listener) {
        return addListener(MakeReservationEvent.class, listener);
    }
    public Registration addCancelReservationListener(ComponentEventListener<CancelReservationEvent> listener) {
        return addListener(CancelReservationEvent.class, listener);
    }
    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}
