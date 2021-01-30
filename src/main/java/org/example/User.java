package org.example;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class User implements IUser, InvocationHandler {
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

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("УАААСЯ БЛЯ");
        return null;
    }

    public void getLog(){
        System.out.println("Standart method");
    }
}
