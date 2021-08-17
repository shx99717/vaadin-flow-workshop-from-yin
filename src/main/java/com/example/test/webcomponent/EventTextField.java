package com.example.test.webcomponent;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.shared.Registration;

@Tag("input")
public class EventTextField extends Component {
    public Registration addChangeListener(
            ComponentEventListener<ChangeEvent> listener) {
           return addListener(ChangeEvent.class, listener);
       }
}
