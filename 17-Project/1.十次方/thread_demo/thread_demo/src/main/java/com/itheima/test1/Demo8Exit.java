package com.itheima.test1;

public class Demo8Exit {

    public static boolean exit = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (exit) {
                    try {
                        System.out.println("线程执行！");
                        Thread.sleep(100l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();

        Thread.sleep(1000l);
        exit = false;
        System.out.println("退出标识位设置成功");
    }
}