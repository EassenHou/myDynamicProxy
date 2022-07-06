package cn.eassen.mydynamicproxy;

import cn.eassen.mydynamicproxy.cglibProxy.MyCglibProxyFactory;
import cn.eassen.mydynamicproxy.jdkProxy.MyJdkProxyHandler;
import cn.eassen.mydynamicproxy.service.StudentService;
import cn.eassen.mydynamicproxy.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author eassen
 * @Create 2022/7/6 15:39
 */
@Component
public class HelloDemo {


    @Autowired
    StudentService studentService;


    public void sayHelloByProxy(String mode) {
        switch (mode){
            case "JDK":
                proxyByJDK();
                break;
            case "CGLIB":
                proxyByCGLIB();
                break;
            case "JAVAASIST":
                proxyByJAVAASIST();
                break;
            case "ASM":
                proxyByASM();
                break;
            default:
                System.out.println("nothing");
        }
    }

    private static void proxyByJDK(){
        // 被代理对象
        StudentService studentService = new StudentServiceImpl();
        // 被代理对象类加载器
        ClassLoader classLoader = studentService.getClass().getClassLoader();
        // 被代理对象接口集合
        Class<?>[] interfaces = studentService.getClass().getInterfaces();

        // 请求处理器， 处理所有的代理对象上的方法调用
        MyJdkProxyHandler myJdkProxyHandler = new MyJdkProxyHandler(new StudentServiceImpl());

        // 创建代理对象
        StudentService studentServiceProxy = (StudentService) Proxy.newProxyInstance(classLoader, interfaces, myJdkProxyHandler);
        studentServiceProxy.getStudents();
    }

    private static void proxyByCGLIB(){
        StudentService studentService = new StudentServiceImpl();
        MyCglibProxyFactory<StudentService> proxyFactory = new MyCglibProxyFactory<>(studentService);
        StudentService proxy = proxyFactory.getProxy();
        proxy.getStudents();
    }

    private static void proxyByJAVAASIST(){

    }

    private static void proxyByASM(){

    }

}
