package com.potato.concurrency.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CyclicBarrierExample2 {

	private static final int threadCount = 30;
	private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < threadCount; i++) {
			Thread.sleep(1000);
			final int tmp = i;
			exec.execute(() -> {
				try {
					test(tmp);
				} catch (Exception e) {
					log.error("exception", e);
				}
			});
		}
		log.info("finished");
		exec.shutdown();

	}

	public static void test(int i) throws Exception {
		log.info("{} is ready", i);
		Thread.sleep(1000);
		try {
			cyclicBarrier.await(2, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.warn("barrier exception ", e);
		}
		log.info("{} continues", i);
	}
}
