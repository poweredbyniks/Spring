package org.example;


public class MyInterceptor {
    User user;

    public MyInterceptor(User user) {
        this.user = user;
    }

    public String getName() {
        return "Прокси в деле";
    }

    public String getAddress() {
        return "и с адресом тоже";
    }

}
