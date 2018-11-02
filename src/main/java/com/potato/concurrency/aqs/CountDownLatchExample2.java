package com.potato.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample2 {

	private static final int threadCount = 100;
	private static final CountDownLatch countDownLatch = new CountDownLatch(20);

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < threadCount; i++) {
			final int tmp = i;
			exec.execute(() -> {
				try {
					test(tmp);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			countDownLatch.countDown();
		}
		try {
			countDownLatch.await(10, TimeUnit.SECONDS); // await后剩下的线程还是会启动执行
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			log.info("finish");
			exec.shutdown();
		}

	}

	public static void test(int i) throws InterruptedException {
		Thread.sleep(100);
		log.info("i = {}", i);
	}
}
