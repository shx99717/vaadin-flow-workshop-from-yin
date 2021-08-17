package com.example.test.layout;

import java.util.List;
import java.util.Map;

import com.example.test.MainView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouteParameterRegex;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.router.RouterLink;

@Route(value="router1/:id([0-9]*)/:path", layout=MenuLayout.class)
@RouteAlias(value="router2/:id("+RouteParameterRegex.BOOLEAN+")", layout=HomeLayout.class)
@RouteAlias("router3/:id?")
@RoutePrefix("test")
@PageTitle("RouterTest1 Page")
public class RouterTest1 extends VerticalLayout implements BeforeEnterObserver , BeforeLeaveObserver, AfterNavigationObserver{
    private HorizontalLayout menuFlexLayout = new HorizontalLayout();
    Div paraDev = new Div();
    public RouterTest1() {
        menuFlexLayout.add(new RouterLink("Back to Home", MainView.class));
        menuFlexLayout.setSpacing(true);
        menuFlexLayout.setMargin(true);
        add(menuFlexLayout,paraDev);
    }
    
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        
      Class<?> targClass =  event.getNavigationTarget();
      Location location = event.getLocation();
      QueryParameters queryParameters = location
              .getQueryParameters();

      Map<String, List<String>> parametersMap =
              queryParameters.getParameters();

      //Notification.show(event.getRouteParameters().toString(), 2000, Position.TOP_CENTER);
      
      //Notification.show(parametersMap.toString(), 2000, Position.BOTTOM_CENTER);
      paraDev.setText(String.format("parametersMap is %s parameter is  %s!", parametersMap,event.getRouteParameters().toString()));
      Notification.show("beforeEnter", 2000, Position.MIDDLE);  
    }

    @Override
    public void beforeLeave(BeforeLeaveEvent event) {
        Notification.show("leave the page", 2000, Position.TOP_CENTER);        
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        // TODO Auto-generated method stub
        Notification.show("afterNavigation", 2000, Position.TOP_CENTER);        
    }

}
