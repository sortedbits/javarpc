package com.sortedbits.javarpc.client;

import com.sortedbits.javarpc.RPCRequest;
import com.sortedbits.javarpc.RPCResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class AbstractRPCClient extends AbstractSocketClient<RPCResponse, RPCRequest> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public AbstractRPCClient() {
        super();
    }

    public AbstractRPCClient(String configName) {
        super(configName);
    }

    public <T> T getService(Class<T> service) {
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
            RPCRequest req = new RPCRequest(service, method.getName(), method.getParameterTypes(), args);
            RPCResponse res = submit(req);
            if (res.isError()) {
                throw res.getError();
            }
            return res.getResult();
        }
    }
}
