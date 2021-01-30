package org.example;


public class User implements IUser {
    private String name;
    private String address;


    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }


    public User() {

    }

    @Override
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
