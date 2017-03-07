package com.sortedbits.remoting.rpc;

import com.sortedbits.remoting.AbstractSocketServer;
import com.sortedbits.remoting.ServerConfig;

public abstract class RpcAbstractServer extends AbstractSocketServer<RpcRequest, RpcResponse, RpcServerController> {

    protected RpcAbstractServer(ServerConfig config) {
        super(config);
    }
}
