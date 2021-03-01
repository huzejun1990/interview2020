package com.dream.interview.study.thread2021;


import java.util.concurrent.TimeUnit;

class MyData {
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

    //请注意，此时number前面是加了 volatile修饰的
    public synchronized void addPlusPlus() {
        number++;
    }
}

/**
 * @Author: huzejun
 * @Date: 2021/3/1 20:29
 * <p>
 * 1 验证volatile的可见性
 * 1.1 假如 int number = 0, number 变量之前根本没有添加volatile关键字修饰
 * 1.2 添加了volatile,可以解决可见性问题
 * <p>
 * 2 验证volatile不保证原子性
 * 2.1 原子性指的是什么意思？
 * 不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被插队或者被分割，需要整体完整
 * 要么同时成功，要么同时失败。
 * 2.2 volatile不保证原子性的案例演示
 *
 * 2.3 why
 */
public class VolatileDemo {

    public static void main(String[] args) {

        MyData myData = new MyData();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        //需要等待上面20个线程都全部计算完成后，再用main线程取得最终的结果值看是多少？
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t finally number value: "+myData.number);
    }

    //volatile可以保证可见性，及时通知其他线程，主物理内存的值已经被修改
    private static void seeOkByVolatile() {
        MyData myDate = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            //暂停一会儿线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myDate.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t updated number value: " + myDate.number);
        }, "AAA").start();

        //第2个线程就是我们的main线程
        while (myDate.number == 0) {
            // main线程一直在这里等待循环，直到number的值不再等于零
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over, main get number value: " + myDate.number);
    }

}
