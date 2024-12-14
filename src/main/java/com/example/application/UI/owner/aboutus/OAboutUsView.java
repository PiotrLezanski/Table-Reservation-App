package com.example.application.UI.owner.aboutus;

import com.example.application.UI.common.IView;
import com.example.application.UI.owner.homepage.OMainMenuView;
import com.example.application.globals.Globals;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("about us")
@Route(value = "owner-about-us", layout = OMainMenuView.class)
@RolesAllowed(Globals.ROLE_OWNER)
public class OAboutUsView extends VerticalLayout implements IView
{
    public OAboutUsView()
    {
        configureUI();
        initializeView();
    }
    
    @Override
    public void configureUI() 
    {
        // Main container styling
        setWidthFull();
        setPadding(true);
        setSpacing(true);
    }

    @Override
    public void initializeView() 
    {
        // Description
        Paragraph description = new Paragraph(
                "With Foodie, your restaurant can join a network of dining establishments, " +
                        "empowering customers to reserve tables conveniently while offering you a comprehensive " +
                        "platform to manage bookings efficiently. Our user-friendly interface allows you to monitor " +
                        "reservations, manage table availability, and optimize your restaurant's seating capacity. " +
                        "TableEase is the perfect solution for restaurants of all sizes, from cozy cafes to bustling bistros. " +
                        "Join Foodie today and take the first step towards a more organized, efficient, and profitable future."
        );
        description.getStyle().set("text-align", "justify").set("font-size", "18px");

        // Features Section with images
        HorizontalLayout featuresLayout = new HorizontalLayout();
        featuresLayout.setWidthFull();
        featuresLayout.setSpacing(true);

        // Feature 1
        VerticalLayout feature1 = new VerticalLayout();
        Image feature1Image = new Image("images/booking_management.png", "Manage Bookings");
        feature1Image.setWidth("300px");
        feature1.getStyle().set("align-items", "center");
        feature1.add(feature1Image, new Paragraph("Effortless Booking Management"));

        // Feature 2
        VerticalLayout feature2 = new VerticalLayout();
        Image feature2Image = new Image("images/table_availability.png", "Table Availability");
        feature2Image.setWidth("300px");
        feature2.getStyle().set("align-items", "center");
        feature2.add(feature2Image, new Paragraph("Monitor Table Availability"));

        // Feature 3
        VerticalLayout feature3 = new VerticalLayout();
        Image feature3Image = new Image("images/siting_capacity.png", "Efficiency");
        feature3Image.setWidth("200px");
        feature3.getStyle().set("align-items", "center");
        feature3.add(feature3Image, new Paragraph("Optimize Seating Capacity"));

        featuresLayout.add(feature1, feature2, feature3);

        // Footer Section
        Paragraph footer = new Paragraph("Join Foodie today and revolutionize your restaurant management.");
        footer.getStyle().set("text-align", "center").set("font-weight", "bold").set("margin-top", "20px");

        // Add components to the layout
        add(description, featuresLayout, footer);
    }
}
