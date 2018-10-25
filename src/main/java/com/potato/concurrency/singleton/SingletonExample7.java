package com.potato.concurrency.singleton;

import com.potato.concurrency.annotation.Recommend;
import com.potato.concurrency.annotation.ThreadSafe;

@ThreadSafe
@Recommend
public class SingletonExample7 {

	private SingletonExample7() {}
	
	public static SingletonExample7 getSingleton() {
		return Singleton.INSTANCE.getInstance();
	}

	private enum Singleton {
		INSTANCE;

		private SingletonExample7 example;

		Singleton() { // JVM保证其构造方法只执行一次
			example = new SingletonExample7();
		}

		public SingletonExample7 getInstance() {
			return example;
		}
	}

}
