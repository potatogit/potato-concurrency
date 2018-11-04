package com.potato.concurrency.deadlock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeadLockExample implements Runnable {

	private int flag;

	public static Object a = new Object(), b = new Object();

	DeadLockExample(int f) {
		flag = f;
	}

	public static void main(String[] args) {
		DeadLockExample e1 = new DeadLockExample(1);
		DeadLockExample e2 = new DeadLockExample(2);
		new Thread(e1).start();
		new Thread(e2).start();
	}

	@Override
	public void run() {
		log.info("flag={}", flag);
		if(flag == 1) {
			synchronized (a) {
				log.info("lock a");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (b) {
					log.info("lock b");
				}
			}

		} else if(flag == 2) {
			synchronized (b) {
				log.info("lock b");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (a) {
					log.info("lock a");
				}
			}

		}
	}
}
