package com.example.application.UI.common;

import com.example.application.UI.customer.homepage.CHomePageView;
import com.example.application.UI.owner.homepage.OHomePageView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Route(value = "")
@PermitAll
public class HomeViewFactory extends VerticalLayout
{
    public HomeViewFactory()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String role = authentication.getAuthorities().iterator().next().toString();
            if (role.equals("ROLE_CUSTOMER")) 
            {
                UI.getCurrent().navigate(CHomePageView.class);
            } 
            else if (role.equals("ROLE_OWNER")) 
            {
                UI.getCurrent().navigate(OHomePageView.class);
            }
        }
    }
}