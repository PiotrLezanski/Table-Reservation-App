package com.example.application.UI.common;

import com.vaadin.flow.router.RouterLink;

import java.util.List;

public interface IMainMenuView extends IView
{
    void initializeHeader();
    void initializeSideMenu();
    List<RouterLink> createRouterLinks();
}
