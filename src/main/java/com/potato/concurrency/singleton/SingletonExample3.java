package com.potato.concurrency.singleton;

import com.potato.concurrency.annotation.NotRecommend;
import com.potato.concurrency.annotation.ThreadSafe;

/**
 * 懒汉模式
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

	private static SingletonExample3 example = null;

	private SingletonExample3() {}

	public synchronized static SingletonExample3 getSingleton() {
		if(example == null) {
			example = new SingletonExample3();
		}
		return example;
	}

}
