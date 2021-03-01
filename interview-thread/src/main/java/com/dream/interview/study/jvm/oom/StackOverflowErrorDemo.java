package com.dream.interview.study.jvm.oom;

/**
 * Created by huzejun
 * on 2020/6/19 15:35
 */
public class StackOverflowErrorDemo {

    public static void main(String[] args) {
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError();   // Exception in thread "main" java.lang.StackOverflowError


    }
}
