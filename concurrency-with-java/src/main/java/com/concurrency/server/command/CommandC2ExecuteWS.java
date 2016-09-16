package com.concurrency.server.command;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by lhenr_000 on 15/09/2016.
 */
public class CommandC2ExecuteWS implements Callable<String> {
    private CommandC2ExecuteWS instanceCommand = null;
    private PrintStream outputClient;

    private CommandC2ExecuteWS(PrintStream outputClient) {
        this.outputClient = outputClient;
    }

    public static CommandC2ExecuteWS newCommandC2ExecuteWS(PrintStream outputClient) {
        return new CommandC2ExecuteWS(outputClient);
    }

    @Override
    public String call() throws Exception {
        System.out.println("Executing receiver command - access database");
        outputClient.println("processing command c2");
        Thread.sleep(25000);
        Integer number = new Random().nextInt(100) + 1;
        outputClient.println("Command exected with success - access database");
        return number.toString();
    }
}
