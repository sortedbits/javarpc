package com.sortedbits.javarpc.rpc;
import com.sortedbits.javarpc.server.AbstractSocketServer;
import com.sortedbits.javarpc.server.ServerConfig;

public abstract class AbstractRPCServer extends AbstractSocketServer<RPCRequest, RPCResponse, RPCServerController> {

    protected AbstractRPCServer(ServerConfig config) {
        super(config);
    }
}
