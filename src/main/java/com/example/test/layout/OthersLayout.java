package com.example.test.layout;

import com.example.test.MainView;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

@Route(value="others", layout =HomeLayout.class)
@CssImport(value="./styles/shared-styles.css",themeFor = "vaadin-text-field")
public class OthersLayout extends VerticalLayout {
    private HorizontalLayout menuFlexLayout = new HorizontalLayout();
    private VerticalLayout childLayout = new VerticalLayout();
    public OthersLayout() {
        menuFlexLayout.add(new RouterLink("Back to Home", MainView.class));
        menuFlexLayout.setSpacing(true);
        menuFlexLayout.setMargin(true);
        TextField textField = new TextField("new text");
        textField.addClassName("yintestclass");
        childLayout.add(textField);
        Button toggleButton = new Button("choose");
        add(menuFlexLayout,childLayout,toggleButton);
    }

}
