package com.itheima.test1;

public class Demo7WaitAndNotify {
    public static void main(String[] args) {
        State state = new State();
        InThread inThread = new InThread(state);
        OutThread outThread = new OutThread(state);

        Thread in = new Thread(inThread);
        Thread out = new Thread(outThread);

        in.start();
        out.start();
    }

    // 控制状态
    static class State {
        //状态标识
        public String flag = "车站外";
    }

    static class InThread implements Runnable {
        private State state;

        public InThread(State state) {
            this.state = state;
        }

        public void run() {
            while (true) {
                synchronized (state) {
                    if ("车站内".equals(state.flag)) {
                        try {
                            // 如果在车站内，就不用进站，等待,释放锁
                            state.wait();
                        } catch (Exception e) {
                        }
                    }

                    System.out.println("进站");
                    state.flag = "车站内";
                    // 唤醒state等待的线程
                    state.notify();
                }
            }
        }
    }

    static class OutThread implements Runnable {
        private State state;

        public OutThread(State state) {
            this.state = state;
        }

        public void run() {
            while (true) {
                synchronized (state) {
                    if ("车站外".equals(state.flag)) {
                        try {
                            // 如果在车站外，就不用出站了，等待,释放锁
                            state.wait();
                        } catch (Exception e) {
                        }
                    }
                    System.out.println("出站");
                    state.flag = "车站外";
                    // 唤醒state等待的线程
                    state.notify();
                }
            }
        }
    }
}