package oom;
//本代码通过CGLIB动态生成大量代理类，导致方法去（原空间）内存溢出
import net.sf.cglib.proxy.Enhancer; //CGLIB 的核心类，用于创建代理类。
import net.sf.cglib.proxy.MethodInterceptor; //拦截代理方法的接口。
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 方法区导致的内存溢出  ---class
 * VM Args: -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 */
public class MethodAreaOutOfMemory {

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(TestObject.class);
            enhancer.setUseCache(false); // // 禁用代理类缓存，确保每次生成新类
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
                    return arg3.invokeSuper(arg0, arg2); // 调用原始方法
                }
            });
            enhancer.create(); //这里就是把TestObject 这个类 加载到JVM中（方法区： JDK1.8  元空间）
        }
    }

    public static class TestObject {
        private double a = 34.53;
        private Integer b = 9999999;
    }
}
