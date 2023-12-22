package com.example.application.views.customer;

import com.example.application.globals.Globals;
import com.example.application.security.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import jakarta.annotation.security.PermitAll;

@PermitAll
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
        Image logo = new Image("images/foodie_logo.png", Globals.appName);
        logo.setMaxWidth("120px");
        logo.setHeight("45px");
        logo.addClassNames("mx-s", "my-s");

        H5 text = new H5("x");
        text.getStyle().set("color", "transparent");
        
        Button logoutButton = new Button("Log out", e -> securityService.logout());
        logoutButton.getStyle().set("margin-right", "15px");
        logoutButton.setClassName("transparent_background_button");
        
        HorizontalLayout headerLayout = new HorizontalLayout(
                new DrawerToggle(),
                logo,
                text,
                logoutButton
        );
        
        headerLayout.setWidthFull();
        headerLayout.expand(text);
        
        headerLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        headerLayout.addClassNames("py-10");
        
        addToNavbar(headerLayout);
    }
    
    private void initializeSideMenu()
    {
//        setDrawerOpened(false);
        
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
