package com.concurrency.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by luis.camilo on 12/09/2016.
 */
public class ServerTask {
    private final ServerSocket serverSocket;
    private final ExecutorService threadPool;
    private Boolean isRunning;

    ServerTask() throws IOException {
        System.out.println("---- Begin server -----");
        this.serverSocket = new ServerSocket(12345);
        this.threadPool = Executors.newCachedThreadPool();
        this.isRunning = true;
    }

    public void run() {
        while (this.isRunning) {
            try (Socket socket = serverSocket.accept()) {
                DistribuitorTask distribuitorTask = new DistribuitorTask(socket, this);
                threadPool.execute(distribuitorTask);
                System.out.println("Accepting new client in port " + socket.getPort());
            }catch (Exception e){
                System.out.println("Server is ruunig ?"+this.isRunning);
            }
        }

    }

    public void stop() throws IOException {
        this.isRunning = false;
        serverSocket.close();
        threadPool.shutdown();
    }

    public static void main(String... args) throws IOException {
        ServerTask server = new ServerTask();
        server.run();
        server.stop();
    }
}
