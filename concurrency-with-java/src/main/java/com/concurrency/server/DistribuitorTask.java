package com.concurrency.server;

import java.net.Socket;

/**
 * Created by luis.camilo on 13/09/2016.
 */

public class DistribuitorTask implements Runnable {
    private Socket socket;

    public DistribuitorTask(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println("Distributing tasks "+ socket);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
