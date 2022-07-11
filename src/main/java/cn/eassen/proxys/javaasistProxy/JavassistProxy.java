package cn.eassen.proxys.javaasistProxy;


import cn.eassen.mydynamicproxy.service.StudentService;
import javassist.*;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 动态改变类的结构，或者动态生成类。
 * @Author eassen
 * @Create 2022/7/6 15:57
 */
public class JavassistProxy  {

    /**
     * 通过 Javassist生成类
     * @throws CannotCompileException
     * @throws IOException
     */
    public static void mkClass() throws CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        //创建Programmer类
        CtClass cc= pool.makeClass("cn.eassen.mydynamicproxy.service.StudentService");
        //定义code方法
        CtMethod method = CtNewMethod.make("public void code(){}", cc);
        //插入方法代码
        method.insertBefore("System.out.println(\"I'm a Programmer,Just Coding.....\");");
        cc.addMethod(method);
        //保存生成的字节码
        cc.writeFile(System.getProperty("user.dir"));
    }

    /**
     * 生成代理
     * @throws Exception
     */
    public static Object mkproxy(Class<?> aClass, Object obj)throws Exception{
        ClassPool pool = ClassPool.getDefault();

        CtClass cc = pool.makeClass("cn.eassen.mydynamicproxy.service.StudentServiceImpl");

        //设置接口
        CtClass proxyInterface = pool.get("cn.eassen.mydynamicproxy.service.StudentService");
        cc.setInterfaces(new CtClass[]{proxyInterface});

        //设置Field
//        CtField field = CtField.make("private com.mchen.dynamic.IPay pay;", cc);
//        cc.addField(field);

        CtClass helloClass = pool.get("cn.eassen.mydynamicproxy.service.StudentService");

        CtClass[] arrays = new CtClass[]{helloClass};
        CtConstructor ctc = CtNewConstructor.make(arrays,null,CtNewConstructor.PASS_NONE,null,null, cc);
        //设置构造函数内部信息
        ctc.setBody("{this.pay=$1;}");
        cc.addConstructor(ctc);

        //创建前置方法
        CtMethod beforePay = CtMethod.make("private void beforePay(){}",cc);
        beforePay.setBody("System.out.println(\"javasist before pay\");");
        cc.addMethod(beforePay);

        //创建后置方法
        CtMethod afterPay = CtMethod.make("private void afterPay(){}",cc);
        afterPay.setBody("System.out.println(\"javasist after pay\");");
        cc.addMethod(afterPay);

        //创建 方法
        CtMethod showInfo = CtMethod.make("public void pay(int money) {}", cc);
        showInfo.setBody("{this.beforePay();" +
                "this.pay.pay($1);" +
                "this.afterPay();}");
        cc.addMethod(showInfo);

        //获取动态生成的class
        Class c = cc.toClass();
        //获取构造器
        Constructor constructor= c.getConstructor(aClass);
        //通过构造器实例化
        StudentService o = (StudentService)constructor.newInstance(obj);
        cc.writeFile(System.getProperty("user.dir"));
        return o;
    }

    public Object createProxy(Class<?> proxy, Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(new Class[]{proxy});
        Class<?> proxyClass = proxyFactory.createClass();
        Object javassistProxy = proxyClass.getDeclaredConstructor().newInstance();
        ((ProxyObject) javassistProxy).setHandler(new JavassistInterceptor(obj));
        return javassistProxy;
    }
}
