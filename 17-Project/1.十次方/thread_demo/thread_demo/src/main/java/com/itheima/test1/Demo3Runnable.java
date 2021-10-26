package com.itheima.test1;

public class Demo3Runnable {
    public static boolean exit = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            public void run() {
                String name = Thread.currentThread().getName();
                for (int i = 0; i < 5; i++) {
                    System.out.println(name + "执行内容：" + i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                String name = Thread.currentThread().getName();
                for (int i = 0; i < 5; i++) {
                    System.out.println(name + "执行内容：" + i);
                }
            }
        }).start();

        Thread.sleep(1000l);
    }
}