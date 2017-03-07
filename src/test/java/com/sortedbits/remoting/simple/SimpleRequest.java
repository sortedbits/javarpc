package com.sortedbits.remoting.simple;

import java.io.Serializable;

public class SimpleRequest implements Serializable {

    public String message;

    public SimpleRequest(String message) {
        this.message = message;
    }
}
