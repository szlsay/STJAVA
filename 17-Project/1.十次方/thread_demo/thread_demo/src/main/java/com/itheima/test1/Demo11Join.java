package com.itheima.test1;

import java.util.Random;

public class Demo11Join {
    public static void main(String[] args) {
        JoinThread joinThread = new JoinThread();
        Thread thread1 = new Thread(joinThread, "线程1");
        Thread thread2 = new Thread(joinThread, "线程2");
        Thread thread3 = new Thread(joinThread, "线程3");
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
        } catch (Exception e) {

        }
        for (int i = 0; i < 5; i++) {
            System.out.println("main ---i:" + i);
        }
    }

    static class JoinThread implements Runnable {
        private Random random = new Random();

        public void run() {
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + "内容是:" + i);
            }
        }
    }
}