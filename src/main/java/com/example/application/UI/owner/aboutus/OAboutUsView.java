package com.example.application.UI.owner.aboutus;

import com.example.application.UI.common.IView;
import com.example.application.UI.owner.homepage.OMainMenuView;
import com.example.application.globals.Globals;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
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
        setMargin(false);
        setPadding(false);

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        mainImage = new Image("images/about_us_background.png", "Get to know Foodie better!");
        mainImage.setSizeFull();
    }

    @Override
    public void initializeView() 
    {
        initializeText();

        add(
                mainImage,
                descriptionLayout
        );
    }

    private void initializeText() 
    {
        descriptionLayout = new VerticalLayout();
        descriptionLayout.addClassName("customer-about-us");
        descriptionLayout.getStyle().set("color", "black");
        descriptionLayout.getStyle().set("padding-left", "70vh");
        descriptionLayout.setMargin(false);

        Paragraph description = new Paragraph(
                "With Foodie, your restaurant can join a network of dining establishments, empowering customers to reserve tables conveniently while offering you a comprehensive platform to manage bookings efficiently. " +
                        "Our user-friendly interface allows you to monitor reservations, manage table availability, and optimize your restaurant's seating capacity. " +
                        "TableEase is the perfect solution for restaurants of all sizes, from cozy cafes to bustling bistros. " +
                        "Join Foodie today and take the first step towards a more organized, efficient, and profitable future."
        );
        description.getElement().getStyle().set("font-size", "1.2em");
        
        descriptionLayout.add(
                description
        );
        
    }

    private VerticalLayout descriptionLayout;
    private Image mainImage;
}
