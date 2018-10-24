package com.potato.concurrency.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynchronizedExample1 {

    // 修饰代码块
    public void test1() {
        synchronized (this) {
            for(int i = 0; i < 10; i++ ) {
                log.info("test 1: {}", i);
            }
        }
    }

    // 修饰方法
    public synchronized void test2() {
        for(int i = 0; i < 10; i++ ) {
                log.info("test 2: {}", i);
            }
    }

    public static void main(String[] args) {
        SynchronizedExample1 synchronizedExample1 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            synchronizedExample1.test2();
        });
        executorService.execute(() -> {
            synchronizedExample1.test2();
        });
    }
}
