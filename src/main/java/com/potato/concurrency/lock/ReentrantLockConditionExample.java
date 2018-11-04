package com.potato.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentrantLockConditionExample {

    public static void main(String[] args) {
    	Lock lock = new ReentrantLock();
    	Condition condition = lock.newCondition();

    	new Thread(() -> {
    		try {
    			lock.lock();
    			log.info("get lock, wait signal"); // 1
    			condition.await();
		    } catch (InterruptedException e) {
    			log.error("exception", e);
		    } finally {
    			log.info("get signal"); // 4
    			lock.unlock();
		    }

	    }).start();

	    new Thread(() -> {
		    try {
			    lock.lock();
			    log.info("get lock"); // 2
			    Thread.sleep(2000);

		    } catch (InterruptedException e) {
			    log.error("exception", e);
		    } finally {
		    	log.info("signal all"); // 3
			    condition.signalAll();
			    lock.unlock();
		    }
	    }).start();
    }

}
