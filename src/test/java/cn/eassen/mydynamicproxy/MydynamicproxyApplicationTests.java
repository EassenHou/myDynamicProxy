package cn.eassen.mydynamicproxy;

import cn.eassen.mydynamicproxy.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;

@SpringBootTest
class MydynamicproxyApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    HelloDemo demo;

    @Test
    void fun1() throws Exception {
        System.out.println("调用sayHelloByProxy");
        demo.sayHelloByProxy("JDK");
    }

    @Test
    void fun2() throws Exception {
        System.out.println("调用sayHelloByProxy");
        demo.sayHelloByProxy("CGLIB");
    }

    @Test
    void fun3() throws Exception {
        System.out.println("调用sayHelloByProxy");
        demo.sayHelloByProxy("JAVAASIST");
    }

    @Test
    void fun4() throws Exception {
        System.out.println("调用sayHelloByProxy");
        demo.sayHelloByProxy("ASM");
    }

}
