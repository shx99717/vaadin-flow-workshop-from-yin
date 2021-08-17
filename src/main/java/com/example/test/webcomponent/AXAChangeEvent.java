package com.example.test.webcomponent;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.textfield.TextField;

@DomEvent("click")
public class AXAChangeEvent
        extends ComponentEvent<AXAInputText> {

    public AXAChangeEvent(AXAInputText source,
                       boolean fromClient) {
        super(source, fromClient);
    }

}
