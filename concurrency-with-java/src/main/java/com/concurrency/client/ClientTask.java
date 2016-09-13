package com.concurrency.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by luis.camilo on 12/09/2016.
 */
public class ClientTask {

    public static void main(String... args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        System.out.println("Connection avaliable");

        PrintStream printOut = new PrintStream(socket.getOutputStream());
        printOut.println("Client 1");

        Scanner keyboard = new Scanner(System.in);
        String newLine = keyboard.nextLine();

        printOut.close();
        keyboard.close();
        socket.close();
    }
}
