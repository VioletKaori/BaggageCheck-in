package com.quyon.CustomerException;

public class RouteNotTrue implements OutBounds {
    private String mesDes;
    public RouteNotTrue(String message){
        mesDes = message;
    }

    @Override
    public int getTypeCode() {
        return 0;
    }

    public String getMessage() {
        return mesDes;
    }
}
