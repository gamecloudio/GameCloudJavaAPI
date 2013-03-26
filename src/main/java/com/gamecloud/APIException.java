package com.gamecloud;

@SuppressWarnings("serial")
public class APIException extends Exception {

    public APIException(Exception e) {
        super(e);
    }

    public APIException(String msg){
        super(msg);
    }
}