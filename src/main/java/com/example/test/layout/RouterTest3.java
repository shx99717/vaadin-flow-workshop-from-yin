package com.example.test.layout;

import com.example.test.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouteParam;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLink;

@Route(value="router5", layout=MenuLayout.class)
@PageTitle("RouterTest3 Page")
public class RouterTest3 extends VerticalLayout{

    public RouterTest3() {
        Button button1 = new Button("Navigate to RouterTest2 Page");
      //Navigation 1
        button1.addClickListener(e -> {
           button1.getUI().ifPresent(ui -> ui.navigate(RouterTest2.class, "para2")); 
        });
        //Navigation 2
        String annoString = RouteConfiguration.forSessionScope().getUrl(RouterTest1.class,new RouteParameters(
                new RouteParam("id", "1234"),
                new RouteParam("path", "api")));
        Anchor annolink = new Anchor(annoString, "Annotation Router");
        
        add(new RouterLink("Home", MainView.class));
        add(new Anchor("https://vaadin.com", "Vaadin"));
        add(annolink);
        add(button1);
    }


}
