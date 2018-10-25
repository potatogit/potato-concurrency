package com.potato.concurrency.singleton;

import com.potato.concurrency.annotation.NotThreadSafe;

/**
 * 懒汉模式
 */
@NotThreadSafe
public class SingletonExample1 {

	private static SingletonExample1 example = null;

	private SingletonExample1() {}

	public static SingletonExample1 getSingleton() {
		if(example == null) {
			example = new SingletonExample1();
		}
		return example;
	}

}
