package com.example.test.webcomponent;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;

public class ImageEvent extends ComponentEvent<Image> {
    public ImageEvent(Image image,
            boolean fromClient) {
        super(image, fromClient);
        Notification.show(image.getSrc());
}
}
