package com.sortedbits.remoting.rpc;

import java.io.Serializable;

public class RpcResponse implements Serializable {

    private Object result;

    public RpcResponse(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public boolean isError() {
        return result instanceof Throwable;
    }

    public Throwable getError() {
        return (Throwable)result;
    }
}
