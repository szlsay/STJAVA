package com.itheima.test3;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo6BlockingQueueTest {
    //最大容量为5的数组堵塞队列
    private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(5, true);
    //private static LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(5);

    //倒计时计数器
    private static CountDownLatch producerLatch;
    private static CountDownLatch consumerLatch;

    public static void main(String[] args) {

        producerLatch = new CountDownLatch(10); //state值为10  
        consumerLatch = new CountDownLatch(10); //state值为10  

        Thread t1 = new Thread(new ProducerTask());
        Thread t2 = new Thread(new ConsumerTask());

        //启动线程  
        t1.start();
        t2.start();

        try {
            System.out.println("producer waiting...");
            //进入等待状态，直到state值为0，再继续往下执行
            producerLatch.await();
            System.out.println("producer end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("consumer waiting...");
            //进入等待状态，直到state值为0，再继续往下执行
            consumerLatch.await();
            System.out.println("consumer end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //结束线程  
        t1.interrupt();
        t2.interrupt();

        System.out.println("end");
    }

    //生产者
    static class ProducerTask implements Runnable {
        private Random rnd = new Random();

        @Override
        public void run() {
            try {
                while (true) {
                    int value = rnd.nextInt(100);
                    //如果queue容量已满，则当前线程会堵塞，直到有空间再继续
                    queue.put(value);

                    System.out.println("生产者：" + value);
                    producerLatch.countDown(); //state值减1
                }
            } catch (Exception e) {
            }
        }
    }

    //消费者
    static class ConsumerTask implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    //如果queue为空，则当前线程会堵塞，直到有新数据加入
                    Integer value = queue.take();

                    System.out.println("消费者:" + value);

                    consumerLatch.countDown(); //state值减1
                    TimeUnit.SECONDS.sleep(2); //线程休眠2秒
                }
            } catch (Exception e) {
            }
        }
    }
}