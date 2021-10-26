package com.itheima.test1;

public class Demo6DeadLock {

    public static void main(String[] args) {
        //创建线程任务对象
        Ticket ticket = new Ticket();
        //创建三个窗口对象
        Thread t1 = new Thread(ticket, "窗口1");
        Thread t2 = new Thread(ticket, "窗口2");
        Thread t3 = new Thread(ticket, "窗口3");

        //卖票
        t1.start();
        t2.start();
        t3.start();
    }

    static class Ticket implements Runnable {

        Object lock = new Object();
        private int ticket = 100;

        public void run() {
            String name = Thread.currentThread().getName();
            while (true) {
                if ("窗口1".equals(name)) {
                    synchronized (lock) {
                        sell(name);
                    }
                } else {
                    sell(name);
                }
                if (ticket <= 0) {
                    break;
                }
            }
        }

        private synchronized void sell(String name) {
            synchronized (lock) {
                if (ticket > 0) {
                    System.out.println(name + "卖票：" + ticket);
                    ticket--;
                }
            }
        }
    }
}