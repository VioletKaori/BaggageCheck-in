package com.quyon.CustomerException;

public class RouteNotTrueException extends RuntimeException {
    private String mesDes;
    public RouteNotTrueException(String message){
        super(message);
        mesDes = message;
    }

    public String getMessage() {
        return mesDes;
    }
}
