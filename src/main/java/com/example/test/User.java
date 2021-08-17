package com.example.test;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;

public class User {
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String role;
    private LocalDate dateOfBirth;
    private Address address;
    private Integer age;
    private List<User> childrensList;
    private String phonenumber;
    private String phoneCode;
    private double assets;
    private String comment;
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public List<User> getChildrensList() {
        return childrensList;
    }
    public void setChildrensList(List<User> childrensList) {
        this.childrensList = childrensList;
    }
    public String getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public String getPhoneCode() {
        return phoneCode;
    }
    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }
    public double getAssets() {
        return assets;
    }
    public void setAssets(double assets) {
        this.assets = assets;
    }
    
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    @Override
    public String toString() {
        return "User [email=" + email + ", passwordHash=" + passwordHash + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role
                + ", dateOfBirth=" + dateOfBirth + ", address=" + address + ", age=" + age + ", childrensList=" + childrensList + ", phonenumber=" + phonenumber
                + ", phoneCode=" + phoneCode + ", assets=" + assets + "]";
    }
    public boolean test(User person) {
        if (firstName!=null&&firstName.length() > 0 && !StringUtils
                .containsIgnoreCase(String.valueOf(person.getFirstName()),
                        firstName)) {
              return false;
          }
        return true;
    }
}
