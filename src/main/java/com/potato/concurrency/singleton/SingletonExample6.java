package com.potato.concurrency.singleton;

import com.potato.concurrency.annotation.ThreadSafe;

/**
 * 饿汉模式
 */
@ThreadSafe
public class SingletonExample6 {

	private static SingletonExample6 example = null;
	static {
		example = new SingletonExample6();
	}

	private SingletonExample6() {}
	
	public static SingletonExample6 getSingleton() {
		return example;
	}

}
