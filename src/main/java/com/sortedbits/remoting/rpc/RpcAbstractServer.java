package com.sortedbits.remoting.rpc;

import com.sortedbits.remoting.AbstractServer;

public abstract class RpcAbstractServer extends AbstractServer<RpcRequest, RpcResponse, RpcServerController> {

    protected RpcAbstractServer() {
        super(new RpcServerController());
    }

}
