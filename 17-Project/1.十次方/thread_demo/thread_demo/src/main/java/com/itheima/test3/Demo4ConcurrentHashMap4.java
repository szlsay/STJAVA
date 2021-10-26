package com.itheima.test3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Demo4ConcurrentHashMap4 {
    public static void main(String[] args) {
        //final Map<String, Integer> count = new HashMap<>();
        final Map<String, Integer> count = new ConcurrentHashMap<>();
        //final Hashtable<String, Integer> count = new Hashtable<>();

        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    count.put("count" + i, 1);
                }
            }
        };
        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                for (Map.Entry<String, Integer> entry : count.entrySet()) {
                    System.out.println(entry.getKey() + ":" + entry.getValue());
                }
            }
        };

        new Thread(task1).start();
        new Thread(task2).start();

        try {
            Thread.sleep(300l);
            System.out.println(count.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}