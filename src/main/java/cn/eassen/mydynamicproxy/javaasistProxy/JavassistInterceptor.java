package cn.eassen.mydynamicproxy.javaasistProxy;

import javassist.util.proxy.MethodHandler;

import java.lang.reflect.Method;

/**
 * @Author eassen
 * @Create 2022/7/6 15:57
 */
public class JavassistInterceptor implements MethodHandler {

    // 被代理对象
    private Object delegate;

    public JavassistInterceptor(Object delegate) {
        this.delegate = delegate;
    }

    /**
     *
     * @param o 创建的代理对象
     * @param m 被代理方法
     * @param args 方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object o, Method m, Method method1, Object[] args) throws Throwable {
        System.out.println("javassist proxy before sing");
        Object ret = m.invoke(delegate, args);
        System.out.println("javassist proxy after sing");
        return ret;
    }
}
