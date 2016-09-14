package com.concurrency.server;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by luis.camilo on 13/09/2016.
 */

public class DistribuitorTask implements Runnable {
    private Socket socket;
    private ServerTask serverTask;

    public DistribuitorTask(Socket socket, ServerTask serverTask) {
        this.socket = socket;
        this.serverTask = serverTask;
    }

    @Override
    public void run() {
        try {
            System.out.println("Distributing tasks " + socket);
            Scanner inputClient = new Scanner(socket.getInputStream());
            PrintStream outPrintStream = new PrintStream(socket.getOutputStream());

            while (inputClient.hasNext()) {
                String command = inputClient.nextLine();
                switch (command) {
                    case "c1":
                        outPrintStream.println("Confirmed Command C1");
                        break;
                    case "c2":
                        outPrintStream.println("Confirmed Command C2");
                        break;
                    case "c3":
                        outPrintStream.println("Confirmed Command C3");
                        break;
                    case "end":
                        outPrintStream.println("Sair");
                        serverTask.stop();
                        break;
                    default:
                        outPrintStream.println("Command not found");
                }
            }
            outPrintStream.close();
            inputClient.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
