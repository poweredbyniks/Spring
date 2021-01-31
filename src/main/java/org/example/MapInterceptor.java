package org.example;

import java.util.Map;

public class MapInterceptor {
    Map map;

    public Map getMap() {
        return map;
    }

    public MapInterceptor(Map map) {
        this.map = map;
    }

    public void getLog() {
        System.out.println("Logging!");
    }

}
