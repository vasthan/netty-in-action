package com.adc.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class IOServer {

    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(8080);

            while (true) {
                Socket socket = server.accept();
                new Thread(() -> {
                    try {
                        int len;
                        byte[] buffer = new byte[1024];
                        InputStream input = socket.getInputStream();
                        while ((len = input.read(buffer)) != -1) {
                            System.out.println(new String(buffer, 0, len));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
