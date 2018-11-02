package com.potato.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreExample3 {

	private static final int threadCount = 30;
	private static final Semaphore semaphore = new Semaphore(3);

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < threadCount; i++) {
			final int tmp = i;
			exec.execute(() -> {
				try {
					if (semaphore.tryAcquire(5, TimeUnit.SECONDS)) {
						test(tmp);
						semaphore.release();
					} else {
						//If can't acquire semaphore, this thread will do nothing.
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		log.info("finished");
		exec.shutdown();

	}

	public static void test(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("i = {}", i);
	}
}
