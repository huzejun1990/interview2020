package com.dream.juc.study;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "********come in callable");
        //暂停一会儿线程
        TimeUnit.SECONDS.sleep(3);
        return 1024;
    }
}

/**
 * Created by huzejun
 * on 2020/6/8 22:21
 * <p>
 * 多线程中，第3种获得多线程的方式
 */
public class CallableDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //两个线程，一个main线程，一个是AA futureTask

        //FutureTase(Callable<V> callable)
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask, "AA").start();
        new Thread(futureTask, "BB").start();
        int result02 = futureTask.get();

        System.out.println(Thread.currentThread().getName() + "***********");
        int result01 = 100;
//        int result02 = futureTask.get();  //要求获取Callable线程的计算结果，如果没有计算完成就要去强求，会导致堵塞，直到计算完成

       /* while (!futureTask.isDone()){

        }*/


//        int result02 = futureTask.get();
        System.out.println("******result: " + (result01 + result02));
    }
}
