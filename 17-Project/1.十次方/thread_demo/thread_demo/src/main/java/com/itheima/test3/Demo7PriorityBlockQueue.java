package com.itheima.test3;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

public class Demo7PriorityBlockQueue {

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<User> queue = new PriorityBlockingQueue<User>();

        PriorityDemo demo = new PriorityDemo(queue);

        for (int i = 0; i < 5; i++) {
            new Thread(demo).start();
        }

        Thread.sleep(100);

        User u = queue.poll();
        while (u != null) {
            System.out.println("优先级是：" + u.getPriority() + "," + u.getUsername());
            u = queue.poll();
        }
    }

    static class PriorityDemo implements Runnable {

        PriorityBlockingQueue queue;
        Random r = new Random();

        public PriorityDemo(PriorityBlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                User user = new User();
                user.setPriority(r.nextInt(100));
                user.setUsername("张三" + i);

                queue.add(user);
            }
        }
    }

    static class User implements Comparable<User> {

        private Integer priority;
        private String username;

        @Override
        public int compareTo(User user) {
            //System.out.println("比较结果"+this.priority.compareTo(user.getPriority()));
            return this.priority.compareTo(user.getPriority());
        }

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}