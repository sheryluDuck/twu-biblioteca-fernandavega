package com.twu.user;

import java.util.Objects;

public class User {

    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String libraryNumber;
    //for now password is just a string, we can use an interface named profile to have
    //authentication
    private String password;
    private UserType userType;

    public User(String name, String email, String address, String phoneNumber, String libraryNumber, String password, UserType userType) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getName(){
        return this.name;
    }


    public boolean isValidPassword(String libraryNumber, String password){
        return Objects.hash(this.libraryNumber, this.password) == Objects.hash(libraryNumber, password);
    }

    public String getLibraryNumber(){
        return this.libraryNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + '\n' +
                "Email: " + email + '\n' +
                "Address: " + address + '\n' +
                "Phone Number: " + phoneNumber + '\n';
    }
}
