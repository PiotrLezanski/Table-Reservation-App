package com.example.application.UI.customer.homepage;

import com.example.application.UI.common.IMainMenuView;
import com.example.application.UI.customer.aboutus.CAboutUsView;
import com.example.application.UI.customer.restaurants.RestaurantListView;
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
import com.vaadin.flow.router.RouterLink;
import jakarta.annotation.security.RolesAllowed;

import java.util.ArrayList;
import java.util.List;

@RolesAllowed(Globals.ROLE_CUSTOMER)
public class CMainMenuView extends AppLayout implements IMainMenuView
{
    public CMainMenuView(SecurityService securityService)
    {
        this.securityService = securityService;
        
        initializeView();
        configureUI();
    }

    @Override
    public void configureUI()
    {
        headerLayout.setWidthFull();
        headerLayout.expand(text);

        headerLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        headerLayout.addClassNames("py-10");

        logoutButton.getStyle().set("margin-right", "15px");
        logoutButton.setClassName("transparent-background-button");
    }

    @Override
    public void initializeView()
    {
        initializeHeader();
        initializeSideMenu();
    }
    
    @Override
    public void initializeHeader()
    {
        Image logo = new Image("images/Foodie_logo.png", Globals.appName);
        logo.setMaxWidth("120px");
        logo.setHeight("45px");
        logo.addClassNames("mx-s", "my-s");

        text = new H5("x");
        text.getStyle().set("color", "transparent");
        
        logoutButton = new Button("Log out", e -> securityService.logout());
        
        headerLayout = new HorizontalLayout(
                new DrawerToggle(),
                logo,
                text,
                logoutButton
        );
        
        addToNavbar(headerLayout);
    }
    
    @Override
    public void initializeSideMenu()
    {
        // by default menu is closed
        setDrawerOpened(false);
        
        var routerLinks = createRouterLinks();
        
        var verticalLayout = new VerticalLayout();
        verticalLayout.getStyle().set("font-size", "1.1em");
        
        for(var link : routerLinks)
        {
            verticalLayout.add(link);
        }
        
        addToDrawer(verticalLayout);
    }
    
    @Override
    public List<RouterLink> createRouterLinks()
    {
        List<RouterLink> routerLinks = new ArrayList<>();
        
        routerLinks.add(new RouterLink("Home Page", CHomePageView.class));
        routerLinks.add(new RouterLink("About Us", CAboutUsView.class));
        routerLinks.add(new RouterLink("Restaurants", RestaurantListView.class));
        
        return routerLinks;
    }
    
    private H5 text;
    private Button logoutButton;
    private HorizontalLayout headerLayout;

    private SecurityService securityService;
}
