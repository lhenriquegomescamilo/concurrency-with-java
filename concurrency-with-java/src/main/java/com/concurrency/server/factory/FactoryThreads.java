package com.concurrency.server.factory;

import java.util.concurrent.ThreadFactory;

/**
 * Created by lhenr_000 on 16/09/2016.
 */
public class FactoryThreads implements ThreadFactory {
    private static Integer number = 0;

    private FactoryThreads() {

    }

    public static FactoryThreads newFactory() {
        return new FactoryThreads();
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, " Thread server tasks  " + number);
        number++;
        thread.setUncaughtExceptionHandler((currentThread, throwable) -> {
            System.out.println("Error in thread " + currentThread.getName() + " with message " + throwable.getMessage());
        });
        return thread;
    }
}
