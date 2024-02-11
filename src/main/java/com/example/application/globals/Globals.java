package com.example.application.globals;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

public class Globals 
{
    // main color: #39ab82
    public static final String appName = "Foodie";
    public static final String mainColor = "#39ab82";
    public static final String secondaryColor = "#B4FAFA";
    public static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";
    public static final String ROLE_OWNER = "ROLE_OWNER";
    
    
    public static void showPopup(String message, NotificationVariant variant, Notification.Position position)
    {
        final int NOTIFICATION_DURATION = 2500;
        Notification notification = new Notification(message, NOTIFICATION_DURATION, position);
        notification.addThemeVariants(variant);
        notification.open();
    }
}
