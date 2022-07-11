package cn.eassen.mydynamicproxy.javaasistProxy;

import javassist.util.proxy.MethodHandler;

import java.lang.reflect.Method;

/**
 * @Author eassen
 * @Create 2022/7/11 15:53
 */
public class JavassistInterceptor implements MethodHandler {

    // 被代理对象
    private Object delegate;

    JavassistInterceptor(Object delegate) {
        this.delegate = delegate;
    }

    /**
     * @param self 创建的代理对象
     * @param m 被代理方法
     * @param proceed 如果代理接口，此参数为null，如果代理类，此参数为父类的方法
     * @param args 方法参数
     */
    @Override
    public Object invoke(Object self, Method m, Method proceed,
                         Object[] args) throws Throwable {
        System.out.println("javassist proxy before sing");
        Object ret = m.invoke(delegate, args);
        System.out.println("javassist proxy after sing");
        return ret;
    }
}