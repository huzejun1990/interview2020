package com.dream.interview.study.jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by huzejun
 * on 2020/6/19 19:12
 * JVM参数
 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 * JVM参数：-XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 * <p>
 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 * <p>
 * Java 8及之后的版本使用Metaspace来替代永久代。
 * <p>
 * Metaspace是方式区在HotSpot中的实现，它与持久代最大的区别在于：Metaspace并不在虚拟机内存中使用本地内存
 * 也就是在java8中，class metadata(the virtual machines internam presentation of Java class),被存储在叫做
 * Metaspace的native memory
 * <p>
 * 永久代（Java 8 后被原空间Metaspace取代了）存放了以下信息
 * <p>
 * 虚拟机加载的类信息
 * 常量池
 * 静态变量
 * 即时编译后的代码
 * <p>
 * 模拟Metaspace空间溢出，我们不断生成类往元空间灌，类占据的空间总是会超过Metaspace指定的空间大小的
 */
public class MetaspaceOOMTest {
    static class OOMTest {
    }

    public static void main(String[] args) {

        int i = 0; //模拟计算数多少次之后发生异常

        try {

            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);

                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable e) {
            System.out.println("******** 多少次后发生异常：" + i);
            e.printStackTrace();
        }

    }

}
