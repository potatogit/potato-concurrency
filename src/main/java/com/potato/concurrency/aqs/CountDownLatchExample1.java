package com.potato.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CountDownLatchExample1 {

	private static final int threadCount = 100;
	private static final CountDownLatch countDownLatch = new CountDownLatch(20);

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < threadCount; i++) {
			final int tmp = i;
			exec.execute(() -> test(tmp));
			countDownLatch.countDown();
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			log.info("finish");
			exec.shutdown();
		}

	}

	public static void test(int i) {
		log.info("i = {}", i);
	}
}
