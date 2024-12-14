package com.example.application.UI.common;

import com.example.application.UI.customer.homepage.CHomePageView;
import com.example.application.UI.owner.homepage.OHomePageView;
import com.example.application.globals.Globals;
import com.vaadin.flow.component.AttachEvent;
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
            if (role.equals("ROLE_" + Globals.ROLE_CUSTOMER))
            {
                UI.getCurrent().getPage().setLocation("customer-homepage");
            }
            else if (role.equals("ROLE_" + Globals.ROLE_OWNER))
            {
                UI.getCurrent().getPage().setLocation("owner-homepage");
            }
        }
    }
}