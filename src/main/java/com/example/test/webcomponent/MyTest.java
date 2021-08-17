package com.example.test.webcomponent;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;

@Tag("my-test-element")
@JsModule("my-test-element/my-test-element.js")
public class MyTest extends LitTemplate  {
    private String prop1;
    private Binder<MyTest> binder;

    @Id("content")
    private Div content;
    
    @Id("helloButton")
    private NativeButton  helloButton;
    
    @Id("comments")
    private TextField comments;
    
    public String getProp1() {
        return prop1;
    }

    public void setProp1(String prop1) {
        this.prop1 = prop1;
    }

    public MyTest(String prop1,Component contentComponent) {
        Element label = ElementFactory.createLabel("Main layout header");
        Element button = ElementFactory.createButton("Click me");
        getElement().appendChild(label, button);

        this.content.removeAll();
        this.prop1 = prop1;
        getElement().setProperty("prop1", prop1);
        helloButton.addClickListener( e ->{
           
            getElement().callJsFunction("clientmethod").then( json -> {
                Notification.show("hello"+json.asString()); 
            });
//           getElement().executeJs("this.clientmethod()").then(String.class, p ->{
//               Notification.show("hello"+p); 
//           });;
        });

        content.add(contentComponent);
        
        
    }

    public TextField getComments() {
        return comments;
    }

    public void setComments(TextField comments) {
        this.comments = comments;
    }
    
    @ClientCallable
    public String getGreeting(String a) {
        return a + this.comments.getValue();
    }
    
}