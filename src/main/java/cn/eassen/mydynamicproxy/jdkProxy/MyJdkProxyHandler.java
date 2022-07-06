package cn.eassen.mydynamicproxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 核心原理： 反射
 *
 * 创建JDK动态代理需要先实现InvocationHandler接口， 并重写其中的invoke方法，
 *
 * 需要实现InvocationHandler接口， 并重写invoke方法。
 * 被代理类需要实现接口， 它不支持继承。
 * JDK 动态代理类不需要事先定义好， 而是在运行期间动态生成。
 * JDK 动态代理不需要实现和被代理类一样的接口， 所以可以绑定多个被代理类。
 *
 * * @Author eassen
 * @Create 2022/7/6 17:12
 */
public class MyJdkProxyHandler implements InvocationHandler {

    /**
     * 需要被代理的对象
     */
    private Object target;

    public MyJdkProxyHandler(Object target) {
        this.target = target;
    }

    /**
     *
     * @param proxy
     * @param method
     * @param args 执行参数
     * @return result 代理对象
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    void before(){
        System.out.println("代理前置处理");
    }

    void after(){
        System.out.println("代理后置处理");
    }
}
