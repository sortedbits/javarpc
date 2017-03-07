package com.sortedbits.javarpc.server;
import com.sortedbits.javarpc.RPCRequest;
import com.sortedbits.javarpc.RPCResponse;

public abstract class AbstractRPCServer extends AbstractSocketServer<RPCRequest, RPCResponse, RPCServerController> {

    protected AbstractRPCServer(ServerConfig config) {
        super(config);
    }
}
