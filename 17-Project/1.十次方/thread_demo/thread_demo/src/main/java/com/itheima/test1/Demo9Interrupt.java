package com.itheima.test1;

public class Demo9Interrupt {

    public static boolean exit = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (exit) {
                    try {
                        System.out.println("线程执行！");

                        //判断线程的中断标志来退出循环
                        if (Thread.currentThread().isInterrupted()) {
                            break;
                        }

                        Thread.sleep(100l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        //线程处于阻塞状态,当调用线程的interrupt()方法时，
                        //会抛出InterruptException异常,跳出循环
                        break;
                    }
                }
            }
        });
        t.start();

        Thread.sleep(1000l);
        //中断线程
        t.interrupt();
        System.out.println("线程中断了");
    }
}