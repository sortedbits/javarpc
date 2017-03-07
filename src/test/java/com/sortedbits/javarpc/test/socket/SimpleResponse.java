package com.sortedbits.javarpc.test.socket;

import java.io.Serializable;

public class SimpleResponse implements Serializable  {

    public String message;

    public SimpleResponse(String message) {
        this.message = message;
    }
}
