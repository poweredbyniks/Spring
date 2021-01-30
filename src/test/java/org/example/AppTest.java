package org.example;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class AppTest {

    @Test
    public void testInvocationHandler() {
        User user = new User("Вася", "Home address");
        InvocationHandler handler = (proxy, method, args) -> {
            if (method.getName().equals("getName")) {
                return ((String) method.invoke(user, args)).toUpperCase();
            }
            return method.invoke(user, args);
        };

        IUser userProxy = (IUser) Proxy.newProxyInstance(user.getClass().getClassLoader(),
                User.class.getInterfaces(), handler);
        System.out.println(userProxy.getName());
        //Assertions.assertEquals("ВАСЯ", userProxy.getName());
    }

    @Test
    public void testProxyInstance() {
        Logger log = LoggerFactory.getLogger(DynamicInvocationHandler.class);

        Map<String, String> target = new HashMap<>();
        Map proxyInstance = (Map) Proxy.newProxyInstance(
                DynamicInvocationHandler.class.getClassLoader(),
                new Class[]{Map.class},
                (Object proxy, Method method, Object[] args) -> {
                    log.error("Invoked method: {} args: {}", method.getName(), args);
                    return method.invoke(target, args);
                }
        );

        proxyInstance.put("hello", "world");
        proxyInstance.get("hello");

        Assertions.assertEquals("world", proxyInstance.get("hello"));
    }

    @Test
    public void testByteBuddy() throws IllegalAccessException, InstantiationException {
        User user = new User("Вася", "Home address");
        User userProxy = new ByteBuddy()
                .subclass(User.class)
                .method(ElementMatchers.named("getName").or(ElementMatchers.named("getAddress")))
                .intercept(MethodDelegation.to(new MyInterceptor(user)))
                .make()
                .load(User.class.getClassLoader())
                .getLoaded()
                .newInstance();

        System.out.println(userProxy.getName() + " " + userProxy.getAddress());
    }
}
