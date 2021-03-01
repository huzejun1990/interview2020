package com.dream.interview.study.jvm.gc;

import java.util.Random;

/**
 * Created by huzejun
 * on 2020/6/20 12:49
 * 1
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC       (DefNew + Tenured)
 * <p>
 * 2
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC      (ParNew + Tenured)
 * <p>
 * 7 （理论知道即可，实际中已经被优化掉了，没有了）
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC
 */
public class GCDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("**************GCDemo hello");

        try {
            String str = "dream";
            while (true) {
                str += str + new Random().nextInt(77777777) + new Random().nextInt(888888888);
                str.intern();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
