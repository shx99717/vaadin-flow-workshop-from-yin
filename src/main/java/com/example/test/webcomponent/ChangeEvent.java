package com.example.test.webcomponent;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.textfield.TextField;

@DomEvent("change")
public class ChangeEvent
        extends ComponentEvent<EventTextField> {

    public ChangeEvent(EventTextField source,
                       boolean fromClient,
                       @EventData("event.button") int button) {
        super(source, fromClient);
    }

}
