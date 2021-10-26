package com.itheima.test1;


public class Demo1CreateThread extends Thread {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("-----多线程创建开始-----");
        // 1.创建一个线程
        CreateThread createThread1 = new CreateThread();
        CreateThread createThread2 = new CreateThread();
        // 2.开始执行线程 注意 开启线程不是调用run方法，而是start方法
        System.out.println("-----多线程创建启动-----");
        createThread1.start();
        createThread2.start();
        System.out.println("-----多线程创建结束-----");
    }

    static class CreateThread extends Thread {
        public void run() {
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 5; i++) {
                System.out.println(name + "打印内容是:" + i);
            }
        }
    }
}