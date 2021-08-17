package com.example.test.webcomponent;

import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

@Tag("paper-slider")
@NpmPackage(value = "@polymer/paper-slider",
            version = "3.0.1")
@JsModule("@polymer/paper-slider/paper-slider.js")
public class PaperSlider extends AbstractSinglePropertyField<PaperSlider, Integer> {
    public PaperSlider() {
        super("value", 0, false);
    }
    public void setValue(int v) {
        getElement().setProperty("value", v);
    }
    public void setMaxValue(int v) {
        getElement().setProperty("max", v);
    }
    public void setSecValue(int v) {
        getElement().setProperty("secondary-progress", v);
    }
    public void increment() {
        getElement().callJsFunction("increment");
    }
    public void decrement() {
        getElement().callJsFunction("decrement");
    }    
    
}
