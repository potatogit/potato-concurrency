package com.potato.concurrency.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CyclicBarrierExample3 {

	private static final int threadCount = 30;
	private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(3,
			() -> log.info("this is cyclic barrier callback")
	);

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < threadCount; i++) {
			Thread.sleep(1000);
			final int tmp = i;
			exec.execute(() -> {
				try {
					log.info("{} is ready", tmp);
					cyclicBarrier.await();
					log.info("{} continues", tmp);
					test(tmp);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
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
