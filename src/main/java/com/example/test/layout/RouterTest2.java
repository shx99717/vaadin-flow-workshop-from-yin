package com.example.test.layout;

import java.util.List;
import java.util.Map;

import com.example.test.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouteParameterRegex;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.router.WildcardParameter;

@Route(value="router4", layout=MenuLayout.class)
@PageTitle("RouterTest2 Page")
//@CssImport(value="./styles/shared-styles2.css")
public class RouterTest2 extends VerticalLayout implements HasUrlParameter<String>{
    private HorizontalLayout menuFlexLayout = new HorizontalLayout();
    Div paraDev = new Div();
    public RouterTest2() {
        menuFlexLayout.add(new RouterLink("Back to Home", MainView.class));
        menuFlexLayout.setSpacing(true);
        menuFlexLayout.setMargin(true);
        Button toggleButton = new Button("choose");
        add(menuFlexLayout,paraDev,toggleButton);
    }
    /**
     * None
     * @OptionalParameter
     * @WildcardParameter
     */
    @Override
    public void setParameter(BeforeEvent event,@WildcardParameter String parameter) {
        // TODO Auto-generated method stub
        paraDev.setText("parameter is "+parameter);
    }
    

}
