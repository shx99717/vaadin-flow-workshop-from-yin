package com.example.test.webcomponent;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.notification.Notification;

@Tag("axa-input-text")
@JsModule("@axa-ch/input-text/lib/index.js")
public class AXAInputText extends Component {
    public AXAInputText() {
        getElement().setProperty("type", "password");
        getElement().setProperty("label", "password");
        addListener(AXAChangeEvent.class, e -> {
            Notification.show(e.getSource().getElement().getPropertyNames().toString());
        });
    }
}
