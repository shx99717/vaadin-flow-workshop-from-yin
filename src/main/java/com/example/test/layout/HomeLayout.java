package com.example.test.layout;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLayout;

@PageTitle("Demo")
//@Theme(value = Lumo.class, variant = Lumo.LIGHT)
//@Theme(value = Material.class, variant = Material.DARK)
@StyleSheet("context://styles/global.css")
//@Theme(themeFolder = "my-theme")
//@NoTheme
public class HomeLayout extends VerticalLayout implements RouterLayout {
    public HomeLayout() {
        FlexLayout headerFlexLayout = new FlexLayout();
        headerFlexLayout.setWidthFull();
        H4 titleH6 = new H4("Vaadin Demo1");
        titleH6.getStyle().set("color", "green");
        titleH6.getStyle().set("text-align", "center");

        headerFlexLayout.add(titleH6);
        headerFlexLayout.setAlignSelf(Alignment.STRETCH, titleH6);
        add(headerFlexLayout);
    }
} 
