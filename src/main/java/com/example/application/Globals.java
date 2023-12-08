package com.example.application;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

public class Globals 
{
    public static void showPopup(String message, NotificationVariant variant, Notification.Position position)
    {
        final int NOTIFICATION_DURATION = 2500;
        Notification notification = new Notification(message, NOTIFICATION_DURATION, position);
        notification.addThemeVariants(variant);
        notification.open();
    }
}
