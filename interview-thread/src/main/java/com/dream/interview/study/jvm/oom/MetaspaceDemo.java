package com.dream.interview.study.jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by huzejun
 * on 2020/6/19 19:45
 */
public class MetaspaceDemo {

    static class OomTest {
    }

    public static void main(String[] args) {
        int i = 0;
        try {
            while (true) {
                i++;

                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OomTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {

                    @Override
                    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy)
                            throws Throwable {
                        return methodProxy.invokeSuper(obj, objects);
                    }
                });
                enhancer.create();
            }
        } catch (Exception e) {
            System.err.println("创建 " + i + " 次后发生异常！");
            // Caused by: java.lang.OutOfMemoryError: Metaspace
            e.printStackTrace();
        }
    }

}
