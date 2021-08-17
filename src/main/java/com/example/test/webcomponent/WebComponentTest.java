package com.example.test.webcomponent;

import com.example.test.layout.MenuLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;

@Route(value = "wc", layout=MenuLayout.class)
public class WebComponentTest extends VerticalLayout {
    public WebComponentTest() {
        Div paperDiv = new Div();

        PaperSlider paperSlider = new PaperSlider();
        paperSlider.setValue(220);
        paperSlider.setMaxValue(600);
        paperSlider.setSecValue(100);
        paperSlider.addValueChangeListener(e ->{
            paperDiv.setText(e.getValue() +"");
        });
        PaperToggleButton paperToggleButton = new PaperToggleButton();
        Button paperIncreaseButton = new Button("increase");
        paperIncreaseButton.addClickListener(e ->{
            paperSlider.increment();
        });
        Button paperdecreaseButton = new Button("decrease");
        paperdecreaseButton.addClickListener(e ->{
            paperSlider.decrement();
        });
        Button toggleButton = new Button("choose");
        toggleButton.addClickListener(e ->{
            paperToggleButton.toggle(true);
        });
        MyDiv myDiv = new MyDiv();
        MyTest myTest = new MyTest("mytest",myDiv);

        EventTextField textField = new EventTextField();

        Registration registration = textField
                .addChangeListener(e ->
                        System.out.println("Event fired"));
        AXAInputText axaInputText = new AXAInputText();
        add(paperSlider,paperDiv,paperIncreaseButton,paperdecreaseButton,paperToggleButton,toggleButton,myTest,textField,axaInputText);
    }
}
