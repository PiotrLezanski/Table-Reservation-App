package com.example.application.UI.owner.restaurants;

import com.example.application.UI.common.IView;
import com.example.application.UI.owner.homepage.OMainMenuView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("add restaurant")
@Route(value = "add-restaurant", layout = OMainMenuView.class)
@RolesAllowed("ROLE_OWNER")
public class AddRestaurantView extends VerticalLayout implements IView
{
    @Override
    public void configureUI() 
    {
        
    }

    @Override
    public void initializeView() 
    {

    }
}
