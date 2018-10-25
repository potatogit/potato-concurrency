package com.potato.concurrency.singleton;

import com.potato.concurrency.annotation.NotThreadSafe;
import com.potato.concurrency.annotation.ThreadSafe;

/**
 * 饿汉模式
 */
@ThreadSafe
public class SingletonExample2 {

	private static SingletonExample2 example = new SingletonExample2();

	private SingletonExample2() {}
	
	public static SingletonExample2 getSingleton() {
		return example;
	}

}
