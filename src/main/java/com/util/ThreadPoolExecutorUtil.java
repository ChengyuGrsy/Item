package com.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorUtil {
    private static ThreadPoolExecutor executor;
    private ThreadPoolExecutorUtil(){

    }
    public static ThreadPoolExecutor getExecutor(){
        if(executor==null){
            synchronized (ThreadPoolExecutorUtil.class){
                if (executor==null){
                    executor=new ThreadPoolExecutor(10,31,100, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(10000));
                }
            }
        }
        return executor;
    }
}
