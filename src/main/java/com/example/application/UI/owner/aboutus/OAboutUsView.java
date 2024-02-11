package com.example.application.UI.owner.aboutus;

import com.example.application.UI.common.IView;
import com.example.application.UI.owner.homepage.OMainMenuView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("about us")
@Route(value = "owner-about-us", layout = OMainMenuView.class)
@RolesAllowed("ROLE_OWNER")
public class OAboutUsView extends VerticalLayout implements IView
{

    @Override
    public void configureUI() {
        
    }

    @Override
    public void initializeView() {

    }
}
