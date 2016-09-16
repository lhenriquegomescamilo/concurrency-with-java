package com.concurrency.server;

import com.concurrency.server.command.CommandC1;
import com.concurrency.server.command.CommandC2;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

/**
 * Created by luis.camilo on 13/09/2016.
 */

public class DistribuitorTask implements Runnable {
    private ExecutorService threadPool;
    private Socket socket;
    private ServerTask serverTask;

    public DistribuitorTask(ExecutorService threadPool, Socket socket, ServerTask serverTask) {
        this.threadPool = threadPool;
        this.socket = socket;
        this.serverTask = serverTask;
    }

    @Override
    public void run() {
        try {
            System.out.println("Distributing tasks ");
            Scanner inputClient = new Scanner(socket.getInputStream());
            PrintStream outputClient = new PrintStream(socket.getOutputStream());

            while (inputClient.hasNext()) {
                String command = inputClient.nextLine();
                switch (command) {
                    case "c1":
                        outputClient.println("Confirmed Command C1");
                        CommandC1 commandC1 = CommandC1.newCommandC1(outputClient);
                        this.threadPool.execute(commandC1);
                        break;
                    case "c2":
                        outputClient.println("Confirmed Command C2");
                        CommandC2 commandC2 = CommandC2.newCommandC2(outputClient);
                        this.threadPool.execute(commandC2);
                        break;
                    case "end":
                        outputClient.println("Sair");
                        serverTask.stop();
                        break;
                    default:
                        outputClient.println("Command not found");
                }
            }
            outputClient.close();
            inputClient.close();
        } catch (Exception e) {
            System.out.println("type error " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }
}
