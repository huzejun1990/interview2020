package com.dream.interview.study.thread;

import java.util.concurrent.*;

/**
 * Created by huzejun
 * on 2020/6/8 23:28
 * 第4种获得/使用java多线程的方式，线程池
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {

        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        try {

            //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }


    }

    private static void ThreadPoolInit() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);   //一池5个处理线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();   //一池1个处理线程
//        ExecutorService threadPool = Executors.newCachedThreadPool();   //一池多个处理线程


        try {

            //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 1; i <= 20; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
