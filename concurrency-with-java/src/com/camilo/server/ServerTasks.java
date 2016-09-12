package com.camilo.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by luis.camilo on 12/09/2016.
 */
public class ServerTasks {

    public static void main(String... args) throws IOException {
        System.out.println("---- Begin server ----");
        ServerSocket server = new ServerSocket(12345);
        while (true) {
            Socket socket = server.accept();
            System.out.println("Accepting new client in port  "+socket.getPort());
        }
    }
}
