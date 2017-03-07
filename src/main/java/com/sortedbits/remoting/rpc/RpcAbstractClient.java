package com.sortedbits.remoting.rpc;

import com.sortedbits.remoting.AbstractClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class RpcAbstractClient extends AbstractClient<RpcResponse, RpcRequest> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public RpcAbstractClient() {
        super();
    }

    public RpcAbstractClient(String configName) {
        super(configName);
    }

    <T> T getService(Class<T> service) {
        if (!service.isInterface()) {
            throw new RuntimeException(service.getName() + " is not an interface");
        }
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{ service }, new RpcInvocationHandler(service));
    }

    private class RpcInvocationHandler<T> implements InvocationHandler {
        private Class<T> service;

        RpcInvocationHandler(Class<T> service) {
            this.service = service;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            RpcRequest req = new RpcRequest(service, method.getName(), method.getParameterTypes(), args);
            RpcResponse res = submit(req);
            if (res.isError()) {
                throw res.getError();
            }
            return res.getResult();
        }
    }
}
