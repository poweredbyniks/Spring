package org.example;

import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class DynamicInvocationHandler implements InvocationHandler {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(
            DynamicInvocationHandler.class);
    private final Map<String, Method> methods = new HashMap<>();
    private final Object target;

    public DynamicInvocationHandler(Object target) {
        this.target = target;

        for (Method method : target.getClass().getDeclaredMethods()) {
            this.methods.put(method.getName(), method);
        }
    }

    public void name(){
        System.out.println("NAME");
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object result = methods.get(method.getName()).invoke(target, args);
        LOGGER.info("Invoked method: { " + method.getName() + "}");

        return result;
    }
}
