package com.example.application.UI.owner.homepage;

import com.example.application.UI.common.IHomePageView;
import com.example.application.UI.owner.aboutus.OAboutUsView;
import com.example.application.UI.owner.restaurants.AddRestaurantView;
import com.example.application.globals.Globals;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("home-page")
@Route(value = "owner-homepage", layout = OMainMenuView.class)
@RolesAllowed(Globals.ROLE_OWNER)
public class OHomePageView extends VerticalLayout implements IHomePageView
{
    public OHomePageView()
    {
        configureUI();
        initializeView();
    }

    @Override
    public void configureUI()
    {
        setClassName("home-page");

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        setWidth("100%");
        getStyle().set("flex-grow", "1");
    }

    @Override
    public void initializeView() 
    {
        H1 welcomeText = new H1("Welcome to " + Globals.appName + "!");
        welcomeText.getStyle().set("color", "white");

        H2 alias = new H2("App to connect hungry diners with your culinary delights and streamline table reservations effortlessly");
        alias.getStyle().set("color", "white");

        Button learnMoreButton = new Button("Add restaurant", this::addRestaurantButtonClicked);
        learnMoreButton.addClassName("mint-button");

        Button searchButton = new Button("How does it work?", this::learnMoreButtonClicked);
        searchButton.addClassName("mint-border-button");

        add(
                welcomeText,
                alias,
                new HorizontalLayout(
                        learnMoreButton,
                        searchButton
                )
        );
    }

    private void addRestaurantButtonClicked(ClickEvent<Button> buttonClickEvent) 
    {
        UI.getCurrent().navigate(AddRestaurantView.class);
    }

    private void learnMoreButtonClicked(ClickEvent<Button> buttonClickEvent)
    {
        UI.getCurrent().navigate(OAboutUsView.class);
    }
}
