package com.potato.concurrency.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynchronizedExample2 {

    // 修饰代码块
    public void test1() {
        synchronized (SynchronizedExample2.class) {
            for(int i = 0; i < 10; i++ ) {
                log.info("test 1: {}", i);
            }
        }
    }

    // 修饰方法
    public static synchronized void test2() {
        for(int i = 0; i < 10; i++ ) {
                log.info("test 2: {}", i);
            }
    }

    public static void main(String[] args) {
        SynchronizedExample2 synchronizedExample1 = new SynchronizedExample2();
        SynchronizedExample2 synchronizedExample2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            synchronizedExample1.test1();
        });
        executorService.execute(() -> {
            synchronizedExample2.test1();
        });
    }
}
