package com.sortedbits.javarpc.test.simple;

import com.sortedbits.javarpc.server.ServerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketServerController implements ServerController<SimpleRequest, SimpleResponse>{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public SimpleResponse handle(SimpleRequest req) {
        logger.debug("Request: {}", req);
        return new SimpleResponse("ok");
    }
}
