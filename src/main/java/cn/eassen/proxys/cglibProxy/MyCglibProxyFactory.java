package cn.eassen.proxys.cglibProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 1、CgLib，一个强大的，高性能，高质量的代码生成类库，
 * 2、它可以在运行期扩展Java类与实现Java接口
 * 3、（是对ASM的一层封装）。
 * @Author eassen
 * @Create 2022/7/6 17:42
 */
public class MyCglibProxyFactory<T> implements MethodInterceptor {

    private T obj;

    public MyCglibProxyFactory(T obj) {
        this.obj = obj;
    }

    public T getProxy() {
        // 1. 创建Enhancer类对象，它类似于咱们JDK动态代理中的Proxy类，该类就是用来获取代理对象的
        Enhancer enhancer = new Enhancer();
        // 2. 设置父类的字节码对象。为啥子要这样做呢？因为使用CGLIB生成的代理类是属于目标类的子类的，也就是说代理类是要继承自目标类的
        enhancer.setSuperclass(obj.getClass());
        // 3. 设置回调函数
        enhancer.setCallback(this);
        // 4. 创建代理对象
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib前置处理.........");
        Object invoke = method.invoke(obj, objects);
        System.out.println("cglib后置处理");
        return invoke;
    }
}
