package com.concurrency.client;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by luis.camilo on 12/09/2016.
 */
public class ClientTask {

    public static void main(String... args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        System.out.println("Connection avaliable");
        socket.close();;
    }
}
