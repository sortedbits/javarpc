package com.sortedbits.remoting.rpc;


import com.sortedbits.remoting.ServerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RPCServerController implements ServerController<RPCRequest, RPCResponse> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Map<Class<?>, Object> registry = new HashMap<>();

    @Override
    public RPCResponse handle(RPCRequest req){
        try {
            Class<?> service = req.getService();
            String methodName = req.getMethodName();
            Class<?>[] paramTypes = req.getParamTypes();
            Object[] args = req.getArgs();
            Object serviceImpl = registry.get(service);
            Method method = service.getMethod(methodName, paramTypes);
            Object result = method.invoke(serviceImpl, args);
            return new RPCResponse(result);
        } catch (InvocationTargetException e) {
            logger.error("Error processing request: ", e.getTargetException());
            return new RPCResponse(e.getTargetException());
        } catch (Exception e) {
            logger.error("Error processing request: ", e);
            return new RPCResponse(e);
        }
    }

    protected void register(Class<?> service, Object serviceImpl) {
        if (!service.isInterface()) {
            throw new RuntimeException(service.getName() + " is not an interface");
        }
        if (!service.isInstance(serviceImpl)) {
            throw new RuntimeException(serviceImpl.getClass().getName() + " does not implement " + service.getName());
        }
        registry.put(service, serviceImpl);
    }
}
