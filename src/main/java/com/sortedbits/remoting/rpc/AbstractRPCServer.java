package com.sortedbits.remoting.rpc;
import com.sortedbits.remoting.AbstractSocketServer;
import com.sortedbits.remoting.ServerConfig;

public abstract class AbstractRPCServer extends AbstractSocketServer<RPCRequest, RPCResponse, RPCServerController> {

    protected AbstractRPCServer(ServerConfig config) {
        super(config);
    }
}
