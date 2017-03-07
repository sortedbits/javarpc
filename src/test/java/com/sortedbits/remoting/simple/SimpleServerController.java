package com.sortedbits.remoting.simple;

import com.sortedbits.remoting.ServerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleServerController implements ServerController<SimpleRequest, SimpleResponse>{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public SimpleResponse handle(SimpleRequest req) {
        logger.debug("Request: {}", req);
        return new SimpleResponse("ok");
    }
}
