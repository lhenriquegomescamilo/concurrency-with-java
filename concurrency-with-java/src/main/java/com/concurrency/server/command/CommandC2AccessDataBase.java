package com.concurrency.server.command;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by lhenr_000 on 15/09/2016.
 */
public class CommandC2AccessDataBase implements Callable<String> {
    private CommandC2AccessDataBase instanceCommand = null;
    private PrintStream outputClient;

    private CommandC2AccessDataBase(PrintStream outputClient) {
        this.outputClient = outputClient;
    }

    public static CommandC2AccessDataBase newCommandC2AccessDataBase(PrintStream outputClient) {
        return new CommandC2AccessDataBase(outputClient);
    }

    @Override
    public String call() throws Exception {
        System.out.println("Executing receiver command C2");
        outputClient.println("processing command c2");
        Thread.sleep(20000);
        Integer number = new Random().nextInt(100) + 1;
        outputClient.println("Command exected with success");
        return number.toString();
    }
}
