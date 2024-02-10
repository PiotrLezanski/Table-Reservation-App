package com.example.application.UI.customer;

import com.example.application.UI.common.IHomePageView;
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
@Route(value = "", layout = CustomerMainMenuView.class)
@RolesAllowed("USER")
public class CustomerHomePageView extends VerticalLayout implements IHomePageView
{
    public CustomerHomePageView()
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
        
        H2 alias = new H2("App to reserve table in your favourite restaurant");
        alias.getStyle().set("color", "white");

        Button learnMoreButton = new Button("Search the list", this::searchTheListButtonClicked);
        learnMoreButton.addClassName("mint-button");
        
        Button searchButton = new Button("Learn more", this::learnMoreButtonClicked);
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
    
    private void learnMoreButtonClicked(ClickEvent<Button> event)
    {
        UI.getCurrent().navigate(AboutUsView.class);
    }

    private void searchTheListButtonClicked(ClickEvent<Button> event)
    {
        UI.getCurrent().navigate(RestaurantListView.class);
    }
}
