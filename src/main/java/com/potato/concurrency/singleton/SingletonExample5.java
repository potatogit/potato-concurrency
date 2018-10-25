package com.potato.concurrency.singleton;

import com.potato.concurrency.annotation.ThreadSafe;

/**
 * 懒汉模式 双重同步锁单例模式
 */
@ThreadSafe
public class SingletonExample5 {

	private volatile static SingletonExample5 example = null;

	private SingletonExample5() {}

	public static SingletonExample5 getSingleton() {
		if(example == null) {
			synchronized (SingletonExample5.class){ //同步锁
				if(example == null) { // 双重检测
					/**
					 * volatile禁止指令重排序
					 */
					example = new SingletonExample5();
				}
			}
		}
		return example;
	}

}
