package com.dream.interview.study.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by huzejun
 * on 2020/6/6 14:42
 * 集合类不安全的问题
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {

        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }

    }

    private static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>(); //Collections.synchronizedSet(new HashSet<>());//new HashSet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
        new HashSet<>().add("a");
    }

    private static void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        // java.util.ConcurrentModificationException

        /*
            1、故障现象
                java.util.ConcurrentModificationException
            2、导致原因
                并发争抢修改导致，参考我们的花名册签名情况
                一个人正在写入，另外一个同学过来抢夺，导致数据不一致异常，并发修改异常

            3、解决方案
                3.1 new Vector<>();
                3.2 Collections.synchronizedList(new ArrayList<>());
                3.3 new CopyOnWriteArrayList<>();

            4、优化建议（同样的错误不犯第2次）
                public E set(int index, E element) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            E oldValue = get(elements, index);

            if (oldValue != element) {
                int len = elements.length;
                Object[] newElements = Arrays.copyOf(elements, len);
                newElements[index] = element;
                setArray(newElements);
            } else {
                // Not quite a no-op; ensures volatile write semantics
                setArray(elements);
            }
            return oldValue;
        } finally {
            lock.unlock();
        }
    }

         */
    }
}
