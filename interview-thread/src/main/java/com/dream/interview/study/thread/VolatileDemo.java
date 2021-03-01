package com.dream.interview.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by huzejun
 * on 2020/6/4 17:13
 */
class MyData {
    volatile int number = 0;

    public void addT060() {
        this.number = 60;
    }

    //请注意，此时number前面加了volatile关键字修饰的 volatile不保证原子性
    public void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void MyAddAtomic() {
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1.验证volatile的可见性
 * 1.1 假如 int number = 0; number变量之前根本没有添加volatile关键字修饰
 * 1.2 添加了volatile,可以解决可见性问题
 * <p>
 * 2.验证volatile不保证原子性
 * 2.1 原子性指的是什么意思？
 * 不可分割，完整性，也就是某个线程正在做某个具体业务时，中间不可以被加塞或者被分割。需要整体完整
 * 要么同时成功，要么同时失败。
 * <p>
 * 2.2 volatile 不保证原子性的案例演示
 * <p>
 * 2.3 why
 * <p>
 * 2.4 如何解决原子性
 * * 加 synchronized
 * * 使用我们的 juc 下AtomicInteger
 */
public class VolatileDemo {

    public static void main(String[] args) {    //main是一切方法的运行入口

        MyData myData = new MyData();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus();
                    myData.MyAddAtomic();
                }

            }, String.valueOf(i)).start();
        }

        //需要等待上面20个线程都全部计算完成后，再用main线取得最终的结果值看是多少
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t int type,finally number value: " + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type,finally number value: " + myData.atomicInteger);
       /* try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }

    //volatile可以保证可见性，及时通知其它线程，主物理内存的值已经被修改
    private static void seeOKByVolatile() {
        MyData myData = new MyData();//资源类

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in ");
            // 暂停一会儿线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addT060();
            System.out.println(Thread.currentThread().getName() + "\t update number value: " + myData.number);
        }, "AA").start();

        //第2个线程就是我们的main线程
        while (myData.number == 0) {
            //main线程就一直在这里等待循环，直到number值不再等于0
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }
}
