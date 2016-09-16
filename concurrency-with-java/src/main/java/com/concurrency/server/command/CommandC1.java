package com.concurrency.server.command;

import java.io.PrintStream;

/**
 * Created by lhenr_000 on 15/09/2016.
 */
public class CommandC1 implements Runnable {
    private CommandC1 instanceCommand = null;
    private PrintStream outputClient;

    private CommandC1(PrintStream outputClient) {
        this.outputClient = outputClient;
    }

    public static CommandC1 newCommandC1(PrintStream outputClient) {
        return new CommandC1(outputClient);
    }

    @Override
    public void run() {
        try {
            System.out.println("Executing command C1");
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        outputClient.println("Command exected with success");
    }
}
