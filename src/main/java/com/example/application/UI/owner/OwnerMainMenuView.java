package com.example.application.UI.owner;

import com.example.application.UI.common.IMainMenuView;
import com.example.application.globals.Globals;
import com.example.application.security.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

@RolesAllowed("OWNER")
public class OwnerMainMenuView extends AppLayout implements IMainMenuView
{
    public OwnerMainMenuView(SecurityService securityService)
    {
        this.securityService = securityService;

        initializeView();
        configureUI();
    }
    
    @Override
    public void initializeHeader()
    {
        Image logo = new Image("images/foodie_logo.png", Globals.appName);
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
    public void initializeSideMenu() {

    }

    @Override
    public List<RouterLink> createRouterLinks() {
        return null;
    }

    @Override
    public void configureUI() {

    }

    @Override
    public void initializeView() {

    }

    private H5 text;
    private Button logoutButton;
    private HorizontalLayout headerLayout;

    private SecurityService securityService;
}
