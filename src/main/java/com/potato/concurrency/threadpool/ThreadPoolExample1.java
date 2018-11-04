package com.potato.concurrency.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadPoolExample1 {
	public static void main(String[] args) {
//		ExecutorService executorService = Executors.newCachedThreadPool();
//		ExecutorService executorService = Executors.newSingleThreadExecutor();
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		for(int i = 0; i < 10; i++) {
			final int tmp = i;
			executorService.execute(() -> {
				log.info("{}", tmp);
			});
		}
		executorService.shutdown();
	}
}
