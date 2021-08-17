package com.example.test.layout;

import com.example.test.MainView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(value="exception", layout =HomeLayout.class)
public class ExceptionLayout extends VerticalLayout implements HasErrorParameter<NotFoundException> {
    private HorizontalLayout menuFlexLayout = new HorizontalLayout();
    private Div childLayout = new Div();
    public ExceptionLayout() {
        menuFlexLayout.add(new RouterLink("Back to Home", MainView.class));
        menuFlexLayout.setSpacing(true);
        menuFlexLayout.setMargin(true);
        add(menuFlexLayout,childLayout);
    }
    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<NotFoundException> parameter) {
        childLayout.setText("could not navigate to '"+event.getLocation().getPath()+"' "+parameter.getCaughtException());
        return 404;
    }

}
