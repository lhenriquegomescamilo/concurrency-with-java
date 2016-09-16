package com.concurrency.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by luis.camilo on 12/09/2016.
 */
public class ClientTask {

    public static void main(String... args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);

        Thread threadSendCommandToServer = new Thread(() -> {
            try {
                System.out.println("You can send command : ");
                PrintStream printOut = new PrintStream(socket.getOutputStream());
                Scanner keyboard = new Scanner(System.in);
                while (keyboard.hasNextLine()) {
                    String newLine = keyboard.nextLine();
                    if (newLine.trim().equals("exit"))
                        break;
                    printOut.println(newLine);
                }
                printOut.close();
                keyboard.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Thread threadReciverResponseOfServer = new Thread(() -> {
            Scanner reponse = null;
            try {
                reponse = new Scanner(socket.getInputStream());
                while (reponse.hasNextLine()) {
                    String reponseServer = reponse.nextLine();
                    System.out.println(reponseServer);
                }
                reponse.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        threadReciverResponseOfServer.start();
        threadSendCommandToServer.start();

        threadReciverResponseOfServer.join();
        socket.close();

    }
}
