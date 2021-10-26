package com.itheima.test2;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;

public class Demo6AtomicMrkableReference {

    public static void main(String[] args) throws InterruptedException {
        User u1 = new User("张三", 22);
        User u2 = new User("李四", 33);

        //和AtomicStampedReference效果一样，用于解决ABA的
        //区别是表示不是用的版本号，而只有true和false两种状态。相当于未修改和已修改
        AtomicMarkableReference<User> amr = new AtomicMarkableReference(u1, true);
        amr.compareAndSet(u1, u2, false, true);

        System.out.println(amr.getReference());

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
