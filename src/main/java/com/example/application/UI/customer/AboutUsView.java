package com.example.application.UI.customer;

import com.example.application.UI.common.IView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;


@PageTitle("about us")
@Route(value = "about-us", layout = CustomerMainMenuView.class)
@RolesAllowed("USER")
public class AboutUsView extends VerticalLayout implements IView
{
    public AboutUsView()
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
    }
    
    @Override
    public void initializeView()
    {
        Image mainImage = new Image("images/about_us_background.png", "Get to know Foodie better!");
        mainImage.setSizeFull();
        
        initializeText();
        
        add(mainImage,
           descriptionLayout
        );
    }
    
    private void initializeText()
    {
        descriptionLayout = new VerticalLayout();
        descriptionLayout.addClassName("about-us");
        descriptionLayout.getStyle().set("color", "black");
        descriptionLayout.getStyle().set("padding-left", "70vh");
        descriptionLayout.setMargin(false);
        
        Paragraph description = new Paragraph(
                "Say farewell to the era of booking tables via phone and anxiously waiting for a waiter's response – with Foodie, the dining game has evolved. " +
                        "Now, securing a table at your favorite restaurant is a breeze, putting you in control of when, where, and how you indulge in your gastronomic delights."
        );
        description.getElement().getStyle().set("font-size", "1.2em");

        Div keyFeatures = new Div(
                new Paragraph("Key Features:"),
                new Paragraph("📅 Effortless Reservations: No more long queues or uncertainty. Foodie empowers you to effortlessly secure a table at your preferred restaurant, " +
                        "ensuring that your dining plans align with your schedule."),
                new Paragraph("🌟 Table Tailoring: Immerse yourself in the dining ambiance you crave. Foodie lets you pick your preferred table, whether it's a cozy corner for an intimate date or a lively spot for a group celebration. Your perfect setting, your way."),
                new Paragraph("🕰️ Time Precision: Time is a precious commodity, and Foodie respects that. Specify the exact time you want to dine, and witness the magic as your chosen restaurant prepares to welcome you at your preferred hour."),
                new Paragraph("🎁 Exclusive Additions: Elevate your Foodie experience with exclusive perks! Enjoy personalized recommendations based on your taste preferences, unlock special offers from partner restaurants, and get a sneak peek at mouthwatering menu highlights before you even set foot in the door."),
                new Paragraph("👨‍💼 Adding Your Restaurant: If you are a restaurant owner and want to add your place to Foodie, you can do this very easily! Just create a \"Restaurant Owner\" account on our site and let a broader audience savor the exceptional experience you have to offer.."),
                new Paragraph("🤩 Something Extra: Foodie goes beyond the ordinary, adding that extra dash of delight to your culinary journey. Whether it's surprise discounts, chef's specials, or insider tips, Foodie keeps the excitement alive with every reservation."),
                new Paragraph("Download Foodie now and transform your dining escapades into unforgettable experiences."),
                new Paragraph("Because every meal should be a celebration, and with Foodie, you're always at the head of the table."),
                new Paragraph("Bon appétit! 🎉")
        );
        keyFeatures.getStyle().set("font-size", "1.2em");
        
        descriptionLayout.add(description, keyFeatures);
    }

    private VerticalLayout descriptionLayout;
}
