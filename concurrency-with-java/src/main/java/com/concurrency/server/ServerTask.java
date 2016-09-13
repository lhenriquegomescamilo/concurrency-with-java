package com.concurrency.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by luis.camilo on 12/09/2016.
 */
public class ServerTask {
    public static void main(String... args) throws IOException {
        System.out.println("---- Begin server -----");
        ServerSocket serverSocket = new ServerSocket(12345);
        ExecutorService threadPool = Executors.newCachedThreadPool();

        while (true) {
            Socket socket = serverSocket.accept();
            DistribuitorTask distribuitorTask = new DistribuitorTask(socket);
            threadPool.execute(distribuitorTask);

            System.out.println("Accepting new client in port " + socket.getPort());
        }
    }
}
