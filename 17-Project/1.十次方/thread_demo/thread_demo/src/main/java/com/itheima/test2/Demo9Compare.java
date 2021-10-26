package com.itheima.test2;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;

public class Demo9Compare {

    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong(0L);
        LongAdder longAdder = new LongAdder();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000000; j++) {
                        //atomicLong.incrementAndGet();
                        longAdder.increment();
                    }
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
        }

        System.out.println(atomicLong.get());
        System.out.println(longAdder.longValue());

        System.out.println("耗时：" + (System.currentTimeMillis() - start));
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
    }
}