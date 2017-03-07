package com.sortedbits.remoting.rpc;

import java.io.Serializable;

public class RPCResponse implements Serializable {

    private Object result;

    public RPCResponse(Object result) {
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
