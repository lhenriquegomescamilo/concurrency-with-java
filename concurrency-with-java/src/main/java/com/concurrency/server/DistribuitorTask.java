package com.concurrency.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

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

            System.out.println("Distributing tasks "+ socket);
            Scanner inputClient = new Scanner(socket.getInputStream());
            while(inputClient.hasNext()){
                String command = inputClient.nextLine();
                System.out.println(command);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
