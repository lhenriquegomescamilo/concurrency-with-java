package com.concurrency.server.command;

import java.io.PrintStream;

/**
 * Created by lhenr_000 on 15/09/2016.
 */
public class CommandC2 implements Runnable {
    private CommandC2 instanceCommand = null;
    private PrintStream outputClient;

    private CommandC2(PrintStream outputClient) {
        this.outputClient = outputClient;
    }

    public static CommandC2 newCommandC2(PrintStream outputClient) {
        return new CommandC2(outputClient);
    }

    @Override
    public void run() {
        try {
            System.out.println("Executing command C2");
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        outputClient.println("Command exected with success");
    }
}
