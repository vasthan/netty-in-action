package com.adc.io;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class IOClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            while (true) {
                socket.getOutputStream().write("hello".getBytes());
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
