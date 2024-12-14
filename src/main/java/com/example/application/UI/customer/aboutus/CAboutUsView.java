package com.example.application.UI.customer.aboutus;

import com.example.application.UI.common.IView;
import com.example.application.UI.customer.homepage.CMainMenuView;
import com.example.application.globals.Globals;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;


@PageTitle("about us")
@Route(value = "customer-about-us", layout = CMainMenuView.class)
@RolesAllowed(Globals.ROLE_CUSTOMER)
public class CAboutUsView extends VerticalLayout implements IView
{
    public CAboutUsView()
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
        // Main image
        Image mainImage = new Image("images/customer_dining.png", "Customer Dining");
        mainImage.setWidth("100%");
        mainImage.getStyle().set("border-radius", "10px").set("margin-bottom", "20px");

        // Intro paragraph
        Paragraph intro = new Paragraph(
                "Say farewell to the era of booking tables via phone and anxiously waiting for a waiter's response – with Foodie, the dining game has evolved. Now, securing a table at your favorite restaurant is a breeze, putting you in control of when, where, and how you indulge in your gastronomic delights."
        );
        intro.getStyle().set("text-align", "justify").set("font-size", "18px");

        // Features Section
        VerticalLayout featuresLayout = new VerticalLayout();
        featuresLayout.setWidthFull();
        featuresLayout.setSpacing(true);

        // Feature 1
        HorizontalLayout feature1 = createFeature("Effortless Reservations",
                "No more long queues or uncertainty. Foodie empowers you to effortlessly secure a table at your preferred restaurant, ensuring that your dining plans align with your schedule.",
                "images/effortless_reservations.png");

        // Feature 2
        HorizontalLayout feature2 = createFeature("Table Tailoring",
                "Immerse yourself in the dining ambiance you crave. Foodie lets you pick your preferred table, whether it's a cozy corner for an intimate date or a lively spot for a group celebration. Your perfect setting, your way.",
                "images/table_tailoring.png");

        // Feature 3
        HorizontalLayout feature3 = createFeature("Time Precision",
                "Time is a precious commodity, and Foodie respects that. Specify the exact time you want to dine, and witness the magic as your chosen restaurant prepares to welcome you at your preferred hour.",
                "images/time_precision.png");

        // Feature 4
        HorizontalLayout feature4 = createFeature("Exclusive Additions",
                "Enjoy personalized recommendations based on your taste preferences, unlock special offers from partner restaurants, and get a sneak peek at menu highlights before you even set foot in the door.",
                "images/dining.png");

        // Feature 5
        HorizontalLayout feature5 = createFeature("Something Extra",
                "Foodie goes beyond the ordinary, adding that extra dash of delight to your culinary journey. Whether it's surprise discounts, chef's specials, or insider tips, Foodie keeps the excitement alive with every reservation.",
                "images/extra.png");

        featuresLayout.add(feature1, feature2, feature3, feature4, feature5);

        // Footer Section
        Paragraph footer = new Paragraph("Download Foodie now and transform your dining escapades into unforgettable experiences. Bon appétit! 🎉");
        footer.getStyle().set("text-align", "center").set("font-weight", "bold").set("margin-top", "20px").set("font-size", "18px");

        // Add components to the layout
        add(mainImage, intro, featuresLayout, footer);
    }

    private HorizontalLayout createFeature(String title, String description, String imagePath)
    {
        Image featureImage = new Image(imagePath, title);
        featureImage.setWidth("230px");
        featureImage.getStyle().set("margin-right", "20px");

        Paragraph titleParagraph = new Paragraph(title);
        titleParagraph.getStyle().set("font-weight", "bold");

        VerticalLayout textLayout = new VerticalLayout();
        textLayout.add(titleParagraph, new Paragraph(description));
        textLayout.setPadding(false);
        textLayout.setSpacing(false);

        HorizontalLayout featureLayout = new HorizontalLayout(featureImage, textLayout);
        featureLayout.setWidthFull();
        featureLayout.setSpacing(true);
        featureLayout.setAlignItems(Alignment.CENTER);

        return featureLayout;
    }
}
