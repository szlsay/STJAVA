package com.itheima.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo3Volatile {

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo demo = new VolatileDemo();

        List<Thread> list = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(demo);
            list.add(t);
            t.start();
        }


        Thread.sleep(1000);

        for (Thread thread : list) {
            thread.interrupt();
        }

        System.out.println(demo.count);
        System.out.println(demo.count.get());
    }

    static class VolatileDemo implements Runnable {
//        public volatile int count;
        public volatile AtomicInteger count = new AtomicInteger(0);

        public void run() {
            addCount();
        }

        //可重入锁
        private Lock lock = new ReentrantLock();

        public void addCount() {
            for (int i = 0; i < 100000000; i++) {
//                synchronized (this) {
//                    lock.lock();
//                    count++;
//                    lock.unlock();
//                }

                //使用原子操作类
                count.incrementAndGet();

                if (Thread.currentThread().interrupted()) {
                    break;
                }
            }
        }
    }
}