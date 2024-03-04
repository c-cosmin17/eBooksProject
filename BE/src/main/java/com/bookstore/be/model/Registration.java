package com.bookstore.be.model;

public class Registration {
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String password;
        private String selectedCardType;
        private String cardNumber;


    public Registration() {
    }

    public Registration(String username, String firstName, String lastName, String email, String phone, String password, String selectedCardType, String cardNumber) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.selectedCardType = selectedCardType;
        this.cardNumber = cardNumber;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSelectedCardType() {
        return selectedCardType;
    }

    public void setSelectedCardType(String selectedCardType) {
        this.selectedCardType = selectedCardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
