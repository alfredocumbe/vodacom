package com.vodacom.challenge.business;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class OnlineThreadPool {

    private static ThreadPoolExecutor pool;
    private static ScheduledExecutorService timer;

    public static ThreadPoolExecutor  getThreadPoolExecutor(){
        if(pool == null) pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        return pool;
    }

    public static ScheduledExecutorService  getTimer(){
        if(timer == null) timer = Executors.newScheduledThreadPool(10);
        return timer;
    }

}