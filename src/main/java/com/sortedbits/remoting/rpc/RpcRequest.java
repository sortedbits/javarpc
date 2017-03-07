package com.sortedbits.remoting.rpc;

import java.io.Serializable;
import java.lang.reflect.Method;

public class RpcRequest implements Serializable {

    protected Class<?> service;
    protected String methodName;


    protected Class<?>[] paramTypes;
    protected Object[] args;

    public RpcRequest(Class<?> service, String methodName, Class<?>[] paramTypes, Object[] args) {
        this.service = service;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.args =args;
    }

    public Class<?> getService() {
        return service;
    }

    public String getMethodName() {
        return methodName;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public Object[] getArgs() {
        return args;
    }
}
