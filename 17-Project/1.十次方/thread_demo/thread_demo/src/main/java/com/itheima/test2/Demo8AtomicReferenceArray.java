package com.itheima.test2;

import java.util.concurrent.atomic.AtomicReferenceArray;

public class Demo8AtomicReferenceArray {

    public static void main(String[] args) throws InterruptedException {
        User u1 = new User("张三", 22);
        User u2 = new User("李四", 33);
        User[] arr = {u1, u2};

        AtomicReferenceArray<User> ara = new AtomicReferenceArray<User>(arr);
        System.out.println(ara.toString());

        User u3 = new User("王五", 44);
        ara.compareAndSet(0, u1, u3);
        System.out.println(ara.toString());
    }

    static class User {
        private String name;
        public volatile int age;

        public User(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
