package com.sortedbits.javarpc.server;


public interface ServerController<I, O> {

    O handle(I req);
}
