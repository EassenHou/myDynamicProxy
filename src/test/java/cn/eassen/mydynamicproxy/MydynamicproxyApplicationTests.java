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
    void fun1() {
        System.out.println("调用sayHelloByProxy");
        demo.sayHelloByProxy();
    }

}
