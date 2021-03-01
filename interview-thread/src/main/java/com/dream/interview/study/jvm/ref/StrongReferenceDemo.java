package com.dream.interview.study.jvm.ref;

/**
 * Created by huzejun
 * on 2020/6/17 22:01
 */
public class StrongReferenceDemo {

    public static void main(String[] args) {
        Object obj1 = new Object(); //这样定义的默认就是强引用
        Object obj2 = obj1; //Obj2引用赋值
        obj1 = null;
        System.gc();
        System.out.println(obj2);
    }
}
