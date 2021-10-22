package com.itheima.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

//传统IO编程，服务端
public class IOServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);

        while (true) {
            //使用阻塞的方式获取新的连接
            Socket socket = serverSocket.accept();

            //每个客户端连接的时候，创建新线程处理
            new Thread() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();

                    try {
                        byte[] data = new byte[1024];
                        InputStream inputStream = socket.getInputStream();

                        while (true) {
                            int len;
                            //使用字节流的方式读取数据
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println("线程" + name + ":" + new String(data, 0, len));
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
