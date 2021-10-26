package com.itheima.test1;

public class Demo10Priorityt {

    public static void main(String[] args) {
        PrioritytThread prioritytThread = new PrioritytThread();

        // 如果8核CPU处理3线程，无论优先级高低，每个线程都是单独一个CPU执行，就无法体现优先级
        // 开启10个线程，让8个CPU处理，这里线程就需要竞争CPU资源，优先级高的能分配更多的CPU资源
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(prioritytThread, "线程" + i);
            if (i == 1) {
                t.setPriority(10);
            }
            if (i == 2) {
                t.setPriority(1);
            }
            t.setDaemon(true);
            t.start();
        }

        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程1总计：" + PrioritytThread.count1);
        System.out.println("线程2总计：" + PrioritytThread.count2);
    }

    static class PrioritytThread implements Runnable {
        public static Integer count1 = 0;
        public static Integer count2 = 0;

        public void run() {
            while (true) {
                if ("线程1".equals(Thread.currentThread().getName())) {
                    count1++;
                }
                if ("线程2".equals(Thread.currentThread().getName())) {
                    count2++;
                }

                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
        }
    }
}