package com.concurrency.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by luis.camilo on 12/09/2016.
 */
public class ServerTask {
    private static final Integer SIZE_THREADPOOL = 4;
    private final ServerSocket serverSocket;
    private final ExecutorService threadPool;
    private AtomicBoolean isRunning;

    ServerTask() throws IOException {
        System.out.println("---- Begin server -----");
        this.serverSocket = new ServerSocket(12345);
        this.isRunning = new AtomicBoolean(true);
        this.threadPool = Executors.newFixedThreadPool(SIZE_THREADPOOL);
    }

    public static void main(String... args) throws IOException {
        ServerTask server = new ServerTask();
        server.run();
        server.stop();
    }

    public void run() {
        while (this.isRunning.get()) {
            try {
                Socket socket = serverSocket.accept();
                DistribuitorTask distribuitorTask = new DistribuitorTask(threadPool,socket, this);
                threadPool.execute(distribuitorTask);
                System.out.println("Accepting new client in port " + socket.getPort());
            } catch (SocketException e) {
                System.out.println("Server is ruunig ? " + this.isRunning.get());
            } catch (IOException e) {
                System.out.println("Server is ruunig ? " + this.isRunning.get());
            }
        }

    }

    public void stop() throws IOException {
        this.isRunning.set(false);
        serverSocket.close();
        threadPool.shutdown();
    }
}
