package com.itheima.test1;

public class Demo2CreateRunnable {

    public static void main(String[] args) {
        System.out.println("-----多线程创建开始-----");
        // 1.创建线程
        CreateRunnable createRunnable = new CreateRunnable();
        Thread thread1 = new Thread(createRunnable);
        Thread thread2 = new Thread(createRunnable);
        thread1.setDaemon(true);
        thread2.setDaemon(true);
        // 2.开始执行线程 注意 开启线程不是调用run方法，而是start方法
        System.out.println("-----多线程创建启动-----");
        thread1.start();
        thread2.start();
        System.out.println("-----多线程创建结束-----");
    }

    static class CreateRunnable implements Runnable {

        public void run() {
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 5; i++) {
                System.out.println(name + "的内容:" + i);
            }
        }
    }
}