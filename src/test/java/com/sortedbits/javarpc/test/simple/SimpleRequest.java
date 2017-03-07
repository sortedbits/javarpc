package com.sortedbits.javarpc.test.simple;

import java.io.Serializable;

public class SimpleRequest implements Serializable {

    public String message;

    public SimpleRequest(String message) {
        this.message = message;
    }
}
