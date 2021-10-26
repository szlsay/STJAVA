package com.itheima.test3;

import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Demo5ConcurrentLinkedQueue {
    public static void main(String[] args) throws Exception {
        Queue<String> queue = new ConcurrentLinkedQueue<String>();
        for (int i = 0; i < 100000; i++) {
            //队列中添加元素
            queue.add(String.valueOf(i));
        }

        QueueDemo1 demo1 = new QueueDemo1(queue);

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(demo1);
            t.start();
        }
    }

    static class QueueDemo1 implements Runnable {
        Queue<String> queue;

        public QueueDemo1(Queue<String> queue) {
            this.queue = queue;
        }

        public void run() {
            String name = Thread.currentThread().getName();
            try {
                long start = new Date().getTime();
                //检索并移除此队列的头，如果此队列为空，则返回 null
                while (queue.poll() != null) {
                    if (queue.size() == 0) {
                    }

                    //if (queue.isEmpty()) {
                    //}
                }
                System.out.println(name+"耗费的时间是:"+(System.currentTimeMillis() - start));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}