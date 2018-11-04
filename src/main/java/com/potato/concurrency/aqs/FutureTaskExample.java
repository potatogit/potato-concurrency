package com.potato.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureTaskExample {
	private static FutureTask<String> futureTask = new FutureTask<>(() -> {
		log.info("potato callable");
		Thread.sleep(5000);
		return "OK";
	});

	public static void main(String[] args) throws Exception {
		new Thread(futureTask).start();
		log.info("main");
		Thread.sleep(1000);
		log.info("future result: {}", futureTask.get());
	}

}
