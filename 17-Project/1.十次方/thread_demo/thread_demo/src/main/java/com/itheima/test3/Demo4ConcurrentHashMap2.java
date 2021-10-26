package com.itheima.test3;

import java.util.HashMap;
import java.util.Map;

public class Demo4ConcurrentHashMap2 {
    public static void main(String[] args) {
        final Map<String, Integer> count = new HashMap<>();
        //final Map<String, Integer> count = new ConcurrentHashMap<>();
        //final Hashtable<String, Integer> count = new Hashtable<>();
        count.put("count", 0);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                //synchronized (count) {
                    int value;
                    for (int i = 0; i < 2000; i++) {
                        value = count.get("count");
                        count.put("count", value + 1);
                    //}
                }
            }
        };
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();

        try {
            Thread.sleep(1000l);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}