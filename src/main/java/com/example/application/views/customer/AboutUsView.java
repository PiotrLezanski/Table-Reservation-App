package com.example.application.views.customer;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;


@PageTitle("about us")
@Route(value = "about-us", layout = MainMenuView.class)
@PermitAll
public class AboutUsView extends VerticalLayout
{
    
}
