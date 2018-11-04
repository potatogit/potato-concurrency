package com.potato.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

@Slf4j
public class StampedLockExample {

    private static Map<String, String> map = new HashMap<>();

	class Point {
		private double x, y;

		final StampedLock sl = new StampedLock();

		//optimistic read lock
		double distanceFromOrigin() {
			long stamp = sl.tryOptimisticRead(); // 乐观读锁貌似不需要释放？
			double curX = x, curY = y;
			if (!sl.validate(stamp)) {
				stamp = sl.readLock(); // 重新获得悲观锁
				try {
					curX = x;
					curY = y;
				} finally {
					sl.unlockRead(stamp);
				}
			}
			return Math.sqrt(curX * curX + curY * curY);
		}

		// pessimistic read lock
		void moveIfAtOrigin(double newX, double newY) {
			long stamp = sl.readLock();
			try {
				while(x == 0.0 && y == 0.0) {
					long writeLockStamp = sl.tryConvertToWriteLock(stamp);
					if(writeLockStamp != 0) {
						stamp = writeLockStamp;
						x = newX;
						y = newY;
						break;
					} else {
						sl.unlockRead(stamp);
						stamp = sl.writeLock();
					}
				}
			} finally {
				sl.unlock(stamp); //此时既可能是读锁，也可能是写锁
			}

		}
	}




}
