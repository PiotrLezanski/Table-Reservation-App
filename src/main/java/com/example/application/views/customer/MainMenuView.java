package com.example.application.views.customer;

import com.example.application.globals.Globals;
import com.example.application.security.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainMenuView extends AppLayout
{
    private SecurityService securityService;
    
    public MainMenuView(SecurityService securityService)
    {
        this.securityService = securityService;
        
        initializeHeader();
        initializeSideMenu();
    }
    
    private void initializeHeader()
    {
        Image logo = new Image("images/Foodie_logo.png", Globals.appName);
        logo.setMaxWidth("120px");
        logo.setHeight("45px");
        logo.addClassNames("mx-s", "my-s");
        
        Button logoutButton = new Button("Log out", e -> securityService.logout());
        logoutButton.setClassName("mr-10");
        
        HorizontalLayout headerLayout = new HorizontalLayout(
                new DrawerToggle(),
                logo,
                logoutButton
        );
        
        headerLayout.setWidthFull();
        headerLayout.expand(logo);
        
        headerLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        headerLayout.addClassNames("py-10");
        
        addToNavbar(headerLayout);
    }
    
    private void initializeSideMenu()
    {
        RouterLink welcomePageLink = new RouterLink("Home Page", HomePageView.class);
        RouterLink aboutusPageLink = new RouterLink("About Us", AboutUsView.class);
        RouterLink restaurantListPageLink = new RouterLink("Restaurants", RestaurantListView.class);
        
        welcomePageLink.setHighlightCondition(HighlightConditions.sameLocation());
        
        addToDrawer(new VerticalLayout(
                    welcomePageLink,
                    aboutusPageLink,
                    restaurantListPageLink
                )
        );
    }
}
