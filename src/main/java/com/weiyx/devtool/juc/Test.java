package com.weiyx.devtool.juc;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
    static TransmittableThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();
    static Executor executor = TtlExecutors.getTtlExecutor(new ThreadPoolExecutor(3, 3, 1,TimeUnit.MINUTES, new LinkedBlockingDeque<>(1), new ThreadPoolExecutor.CallerRunsPolicy()));
    static ThreadPoolExecutor executorFather = new ThreadPoolExecutor(2, 2, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(1), new ThreadPoolExecutor.CallerRunsPolicy());
    public static void main(String[] args) throws InterruptedException {
        System.out.println("111");
        executorFather.execute(() -> {
            threadLocal.set("1");
            for (int i = 0; i < 5; i++) {
                executor.execute(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String o = threadLocal.get();
                    System.out.println("1:"+Thread.currentThread().getName() + ":" + o);
                });
            }
        });

        Thread.sleep(2*1000);
        executorFather.execute(() -> {
            threadLocal.set("2");
            for (int i = 0; i < 5; i++) {
                executor.execute(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String o = threadLocal.get();
                    System.out.println("2:"+Thread.currentThread().getName() + ":" + o);
                });
            }
        });
        Thread.sleep(50*1000);


    }
}