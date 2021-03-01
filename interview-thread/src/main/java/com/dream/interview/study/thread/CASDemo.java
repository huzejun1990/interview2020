package com.dream.interview.study.thread;

/**
 * Created by huzejun
 * on 2020/6/5 0:09
 */

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1. CAS是什么？===> compareAndSet
 * 比较并交换
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        // main do thing......

        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t current data: " + atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5, 1024) + "\t current data: " + atomicInteger.get());

        atomicInteger.getAndIncrement();
    }
}
