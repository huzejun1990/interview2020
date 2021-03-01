package com.dream.interview.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by huzejun
 * on 2020/6/4 20:41
 */
public class T1 {

    volatile int n = 0;

    public void add() {
        n++;
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);
    }
}
