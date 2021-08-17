package com.example.test.webcomponent;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
@Tag("paper-toggle-button")
@NpmPackage(value = "@polymer/paper-toggle-button",
            version = "3.0.1")
@JsModule("@polymer/paper-toggle-button/paper-toggle-button.js")
public class PaperToggleButton extends Component {
        public void toggle(boolean b) {
            getElement().setProperty("checked", b);
        }
}
