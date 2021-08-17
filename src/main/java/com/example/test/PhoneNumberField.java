package com.example.test;

import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

public class PhoneNumberField extends CustomField<String[]>{
    private Select countryCode = new Select();
    private TextField subscriberCode = new TextField();
     
    public PhoneNumberField() {
        setLabel("Phone number");
        setHelperText(
              "Please, provide your phone number with country code");
        countryCode.setItems("+86", "+46", "+34");
        countryCode.getStyle().set("width", "6em");
        countryCode.setPlaceholder("Code");
        subscriberCode.setPattern("[0-9]*");
        subscriberCode.setPreventInvalidInput(true);
        subscriberCode.setMaxLength(8);
        subscriberCode.setWidthFull();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(countryCode, subscriberCode);
        add(horizontalLayout);
    }
    
    @Override
    protected String[] generateModelValue() {
        return new String[]{countryCode.getValue().toString() ,subscriberCode.getValue()};
    }

    @Override
    protected void setPresentationValue(String[] newPresentationValue) {
        if (newPresentationValue == null) {
            countryCode.setValue(newPresentationValue[0]);
            subscriberCode.setValue(newPresentationValue[1]);
        }
    }

    public Select getCountryCode() {
        return countryCode;
    }

    public TextField getSubscriberCode() {
        return subscriberCode;
    }


}
