package com.potato.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class FutureExample {
	static class PotatoCallable implements Callable<String> {
		@Override
		public String call() throws Exception {
			log.info("potato callable");
			Thread.sleep(5000);
			return "OK";
		}
	}

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		Future<String> future = exec.submit(new PotatoCallable());
		log.info("main");
		Thread.sleep(1000);
		String s = future.get();
		log.info("future result: {}", s);
		exec.shutdown();
	}

}
