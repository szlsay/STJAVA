package com.itheima.test2;

public class Demo1Jmm {

    public static void main(String[] args) throws InterruptedException {

        JmmDemo demo = new JmmDemo();
        Thread t = new Thread(demo);
        t.start();
        Thread.sleep(100);
        demo.flag = false;
        System.out.println("已经修改为false");
        System.out.println(demo.flag);
    }

    static class JmmDemo implements Runnable {
//        public boolean flag = true;
        public volatile boolean flag = true;

        public void run() {
            System.out.println("子线程执行。。。");
            while (flag) {
                synchronized (this) {
                }
            }
            System.out.println("子线程结束。。。");
        }
    }
}