package com.example.test.layout;

import com.example.test.MainView;
import com.example.test.webcomponent.WebComponentTest;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouteParam;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@ParentLayout(HomeLayout.class)
public class MenuLayout extends VerticalLayout implements RouterLayout {
    private HorizontalLayout menuFlexLayout = new HorizontalLayout();
    private VerticalLayout childLayout = new VerticalLayout();
    public MenuLayout() {
        menuFlexLayout.add(new RouterLink("Home", MainView.class));
        String annoString = RouteConfiguration.forSessionScope().getUrl(RouterTest1.class,new RouteParameters(
                new RouteParam("id", "1234"),
                new RouteParam("path", "api")));

        Anchor annoStringlink = new Anchor(annoString, "Annotation Router");        
        menuFlexLayout.add(annoStringlink);
        menuFlexLayout.add(new RouterLink("URLParam Router", RouterTest2.class,"para1"));
        menuFlexLayout.add(new RouterLink("Navigation Router", RouterTest3.class));
        menuFlexLayout.add(new RouterLink("Web Component", WebComponentTest.class));
        menuFlexLayout.add(new RouterLink("Others", OthersLayout.class));
        menuFlexLayout.setSpacing(true);
        menuFlexLayout.setMargin(true);
        add(menuFlexLayout,childLayout);
    }
    @Override
    public void showRouterLayoutContent(HasElement content) {
        // TODO Auto-generated method stub
       // RouterLayout.super.showRouterLayoutContent(content);
        childLayout.getElement().appendChild(content.getElement());    
    }

}
