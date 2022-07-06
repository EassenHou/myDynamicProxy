package cn.eassen.mydynamicproxy;

import cn.eassen.mydynamicproxy.entity.Student;
import cn.eassen.mydynamicproxy.proxy.JavassistInterceptor;
import cn.eassen.mydynamicproxy.service.StudentService;
import cn.eassen.mydynamicproxy.service.StudentServiceImpl;
import javassist.compiler.MemberResolver;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author eassen
 * @Create 2022/7/6 15:39
 */
@Component
public class HelloDemo {


    @Autowired
    StudentService studentService;


    public void sayHelloByProxy() {
        proxyByJDK();
    }

    private static void proxyByJDK(){
        StudentServiceImpl impl = new StudentServiceImpl();
        Object proxy = java.lang.reflect.Proxy.newProxyInstance(impl.getClass().getClassLoader(), impl.getClass().getInterfaces(), new java.lang.reflect.InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before:" + method.getName());
                Object result = method.invoke(impl, args);
                System.out.println("after:" + method.getName());
                return result;
            }
        });
        StudentService proxy1 = (StudentService) proxy;
        proxy1.getStudents();
    }

}
