package com.sortedbits.remoting;


public interface ServerController<I, O> {

    O handle(I req);
}
