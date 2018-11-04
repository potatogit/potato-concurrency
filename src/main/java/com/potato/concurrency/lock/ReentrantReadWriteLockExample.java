package com.potato.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class ReentrantReadWriteLockExample {
	/**
	 * ReentrantReadWriteLock 是一个悲观锁，只有当读锁没被占用时，才能获得写锁，
	 * 所以不适合读多写少的情况。若要使用乐观锁，请参考 StampedLock。
	 */
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static Lock readLock = lock.readLock();

    private static Lock writeLock = lock.writeLock();

    private static Map<String, String> map = new HashMap<>();

    public Set<String> getAllKeys() {
    	readLock.lock();
    	try {
    		return map.keySet();
	    } finally {
    		readLock.unlock();
	    }
    }

    public void set(String key, String val) {
    	writeLock.lock();
    	try {
    		map.put(key, val);
	    } finally {
    		writeLock.unlock();
	    }
    }

}
