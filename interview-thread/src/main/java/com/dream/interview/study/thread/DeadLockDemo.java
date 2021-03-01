package com.dream.interview.study.thread;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable {
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有：" + lockA + "\t 偿试获得　" + lockB);
            //暂停一会儿线程
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有：" + lockB + "\t偿试获得 " + lockA);
            }
        }
    }
}

/**
 * Created by huzejun
 * on 2020/6/9 22:22
 * 死锁是指两个或者两个以上的进程在执行过程中，
 * 因为争夺资源而造成的一种互相等待的现象，
 * 若无外力干涉那它们都将无法推进下去
 */
public class DeadLockDemo {

    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "ThreadAAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "ThreadBBB").start();

        /**
         * linux ps -ef|grep xxx
         *
         * windows下的java运行程序 也有类似ps的查看进程命令，但是目前需要查看的只是java
         *
         *          jps = java ps
         */
    }
}
