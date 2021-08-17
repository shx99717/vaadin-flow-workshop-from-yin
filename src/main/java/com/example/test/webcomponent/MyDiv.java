package com.example.test.webcomponent;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

public class MyDiv extends Div {
    public MyDiv() {
        Image image = new Image("icons/icon.png","icon");        
        add(image);
        fireEvent(new ImageEvent(image,false));
    }
}
